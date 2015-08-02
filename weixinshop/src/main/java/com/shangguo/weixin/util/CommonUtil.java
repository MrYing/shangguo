package com.shangguo.weixin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.shangguo.model.user.T_user;
import com.shangguo.util.PropertiesUtil;
import com.shangguo.weixin.accessToke.AccessToken;

/**
 *
 * @author JabnMai
 * @date 2015年7月21日
 * 
 */
public class CommonUtil {
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	// 测试号
	/*
	 * public static String appID ="wx916a1d9dce639147"; public static String
	 * appsecret = "63b8f49e77e95ffdab3baac9615a0c14";
	 */
	// inaiba 的appID和appsecret
	public static String isExistsNextOpenID = null;

	public static String httpsRequest(String requestUrl, String requestMethod,
			String outputStr) {
		HttpsURLConnection conn = null;
		StringBuffer buffer = null;
		try {
			// 创建SSLContext
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			TrustManager[] tm = { new MyX509TrustManager() };
			// 初始化
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 获取SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod(requestMethod);
			// 设置当前实例使用的SSLSocketFactory
			conn.setSSLSocketFactory(ssf);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.connect();
		} catch (Exception e) {
			log.error("发起https请求连接失败");
		}

		// 往服务器写内容
		if (outputStr != null) {
			try {
				OutputStream output = conn.getOutputStream();
				output.write(outputStr.getBytes("utf-8"));
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("往服务器写内容出错");
			}
		}

		// 获取服务器返回的内容
		try {
			InputStream is = conn.getInputStream();
			InputStreamReader isReader = new InputStreamReader(is, "utf-8");
			BufferedReader bufferReader = new BufferedReader(isReader);
			buffer = new StringBuffer();
			String line = null;
			while ((line = bufferReader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("接收服务器返回内容出错");
		}
		return buffer.toString();
	}

	/**
	 * 获取access_token字符串
	 * 
	 * @return String accessToken
	 */
	public static String getAccessToken() {
		AccessToken accessToken = new AccessToken();
		return accessToken.getAccess_token();
	}

	public static String getTempAccessToken() {
		Properties prop = PropertiesUtil
				.readProperties("weixinInterface.properties");
		String tokenUrl = prop.getProperty("access_token_url");
		String appID = prop.getProperty("appID");
		String appsecret = prop.getProperty("appsecret");
		String access_token_url = tokenUrl.replace("APPID", appID).replace(
				"APPSECRET", appsecret);
		String tokenData = httpsRequest(access_token_url, "GET", null);
		JSONObject jsonObject = JSONObject.fromObject(tokenData);
		return jsonObject.getString("access_token");
	}

	
	/**
	 * 批量获取用户openID
	 * @param nextOpenId
	 * if nextOpenId==null 则从头拉取信息，否则指定位置拉取
	 * @return
	 */
	public static List<String> getOpenID(String nextOpenId) {
		List<String> openIDList = new ArrayList<String>();
		String openID = null;
		String urlMode = null;
		if (nextOpenId == null) {
			urlMode = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
		} else {
			urlMode = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid="
					+ nextOpenId;
		}
		String access_token = getTempAccessToken();
		String requestUrl = urlMode.replace("ACCESS_TOKEN", access_token);
		String jsonData = httpsRequest(requestUrl, "GET", null);
		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		if (jsonObject != null) {
			String data = jsonObject.getString("data");
			String nextOpenID = jsonObject.getString("next_openid");
			if (nextOpenID != null) {
				isExistsNextOpenID = null;
			}
			System.out.println(nextOpenID);
			JSONObject dataJsonObject = JSONObject.fromObject(data);
			// System.out.println(jsonObject.toString());
			JSONArray jsonArray = dataJsonObject.getJSONArray("openid");
			Iterator it = jsonArray.iterator();
			while (it.hasNext()) {
				openID = (String) it.next();
				openIDList.add(openID);
			}
			String next_openid = jsonObject.getString("next_openid");
			if (next_openid == null) {
				return openIDList;
			} else {
				getOpenID(next_openid);
			}
		}

		return openIDList;

	}

	public static JSONObject getUserBaseInfo(String accessToken, String openID) {
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="
				+ accessToken + "&openid=" + openID + "&lang=zh_CN";
		String jsonData = httpsRequest(requestUrl, "GET", null);
		JSONObject userBaseJsonObject = JSONObject.fromObject(jsonData);
		return userBaseJsonObject;
	}

	public static void main(String[] args) {
		getOpenID(null);
		// getUserBaseInfo(getAccessToken(),getOpenID());
		/*
		 * String str = getAccessToken(); System.out.println(str);
		 */

	}
}

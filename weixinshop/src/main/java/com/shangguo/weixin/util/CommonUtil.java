package com.shangguo.weixin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

public class CommonUtil {
	public static Logger log = Logger.getLogger(CommonUtil.class);
	//测试号
	/*public static String appID ="wx916a1d9dce639147";
	public static String appsecret = "63b8f49e77e95ffdab3baac9615a0c14";*/
	//inaiba 的appID和appsecret
	public static String appID ="wxd28de7f37cbfa9a3";
	public static String appsecret = "f652ae5d3bd442cc9aa294888f1a72ab";
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr){
    	HttpsURLConnection conn = null;
    	StringBuffer buffer =null;
    	try{
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
    	}catch(Exception e){
    		log.error("发起https请求连接失败");
    	}
    	
    	//往服务器写内容
    	if(outputStr != null){
    		try {
    			OutputStream output = conn.getOutputStream();
    			output.write(outputStr.getBytes("utf-8"));
    			output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("往服务器写内容出错");
			}
    	}
    	
    	//获取服务器返回的内容
    	try {
    		InputStream is = conn.getInputStream();
    		InputStreamReader isReader = new InputStreamReader(is,"utf-8");
    		BufferedReader bufferReader = new BufferedReader(isReader);
    		buffer = new StringBuffer();
    		String line = null;
    		while((line = bufferReader.readLine()) != null){
    			buffer.append(line);
    		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("接收服务器返回内容出错");
		}
        return buffer.toString();
    }
    
    public static String getAccessToken(){
    	String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appID + "&secret=" + appsecret;
    	String access_token = null;
    	String jsonData = httpsRequest(requestUrl, "GET", null);
    	JSONObject jsonObject = JSONObject.fromObject(jsonData);
    	if(jsonObject != null){
    		access_token = jsonObject.getString("access_token");
    	}
    	
    	return access_token;
    }
    
    public static String getOpenID(){
    	String openID = null;
    	String urlMode = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
    	String access_token = getAccessToken();
    	String requestUrl = urlMode.replace("ACCESS_TOKEN", access_token);
    	String jsonData = httpsRequest(requestUrl, "GET", null);
    	JSONObject jsonObject = JSONObject.fromObject(jsonData);
    	if(jsonObject != null){
    		String data = jsonObject.getString("data");
    		JSONObject dataJsonObject = JSONObject.fromObject(data);
    		//System.out.println(jsonObject.toString());
    		JSONArray jsonArray = dataJsonObject.getJSONArray("openid");
    		 Iterator it = jsonArray.iterator();
    		 while(it.hasNext()){
    			 openID = (String) it.next();
    			 System.out.println(openID);
    		 }
    	}
    	return openID;
    }
    
    public static void getUserBaseInfo(String accessToken, String openID){
    	String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid=" + openID + "&lang=zh_CN";
    	String jsonData = httpsRequest(requestUrl, "GET", null);
    	JSONObject jsonObject = JSONObject.fromObject(jsonData);
    	String nickname = jsonObject.getString("nickname");
    	System.out.println(nickname);
    }
    public static void main(String[] args) {
    	//getOpenID();
    	getUserBaseInfo(getAccessToken(),getOpenID());
    	/*String str = getAccessToken();
    	System.out.println(str);*/
	}
}

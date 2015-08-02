package com.shangguo.weixin.thread;

import java.util.Properties;

import org.apache.log4j.Logger;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.shangguo.util.PropertiesUtil;
import com.shangguo.weixin.accessToke.AccessToken;
import com.shangguo.weixin.util.CommonUtil;

/**
 * 定时获取微信access_token的线程
 * @author JabnMai
 * @date 2015年7月14日
 * 
 */
public class TokenThread implements Runnable {
	private static Logger log = Logger.getLogger(TokenThread.class);
	//private static Logger log = LoggerFactory.getLogger(TokenThread.class);
	public static AccessToken accessToken = null;
    private  Properties prop = PropertiesUtil.readProperties("weixinInterface.properties");
	private  String appID = (String)prop.get("appID");
	private  String appsecret = (String)prop.get("appsecret");
	private  String access_token_url = (String)prop.get("access_token_url");
	
	public void run() {
		while (true) {
			try {
				accessToken = getAccessToken();
				if (null != accessToken) {
					log.info("获取access_token成功，有效时长"+accessToken.getExpiresIn()+"秒, token:"+ accessToken.getAccess_token());
					// 休眠7000秒
					//Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);
					Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);
				} else {
					 // 如果access_token为null，60秒后再获取
					Thread.sleep(60 * 1000);
				}
			} catch (InterruptedException e) {
				try {
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e1) {
					log.error("{}", e1);
				}
				log.error("{}", e);
			}
		}
	}
	
	
	public AccessToken getAccessToken() {
		AccessToken accessToken = null;
		String requestUrl = this.access_token_url.replace("APPID", this.appID).replace("APPSECRET", this.appsecret);
		String jsonObjectStr = CommonUtil.httpsRequest(requestUrl, "GET", null);
		JSONObject jsonObject = JSONObject.fromObject(jsonObjectStr);
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setAccess_token(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				log.error("获取token，微信返回的错误码信息errcode:"+jsonObject.getInt("errcode")+" errmsg:"+ jsonObject.getString("errmsg"));
				System.out.println(jsonObject.getInt("errcode")+ "  , " + jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}

	
}

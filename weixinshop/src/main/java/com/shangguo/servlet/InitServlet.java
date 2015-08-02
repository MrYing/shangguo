package com.shangguo.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangguo.util.PropertiesUtil;
import com.shangguo.weixin.thread.TokenThread;

public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = -6235471784685884987L;
	private static Logger log = LoggerFactory.getLogger(InitServlet.class);
	
	public void init() throws ServletException {
		PropertiesUtil.prop = PropertiesUtil.readProperties("weixinInterface.properties");
		String appID = (String) PropertiesUtil.prop.get("appID");
		String appsecret = (String) PropertiesUtil.prop.get("appsecret");
		log.info("获取access_token");
		if("".equals(appID) || "".equals(appsecret)){
			log.error("appid and appsecret configuration error, please check carefully.");
		}else{
		    new Thread(new TokenThread()).start();
		    
		}
	}
	
}

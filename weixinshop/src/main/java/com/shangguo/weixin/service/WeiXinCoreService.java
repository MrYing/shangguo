package com.shangguo.weixin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.shangguo.weixin.message.Article;
import com.shangguo.weixin.message.NewsMessage;
import com.shangguo.weixin.message.TextMessage;
import com.shangguo.weixin.util.MessageUtil;
import com.shangguo.weixin.util.ProjectRootPath;

/**
 * 核心服务类
 * 
 * @author JabnMai
 * @date 2015-7-7
 */
public class WeiXinCoreService {
	
	private static Logger logger = Logger.getLogger(WeiXinCoreService.class);
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		try {
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			String fromUserName = requestMap.get("FromUserName");
			String toUserName = requestMap.get("ToUserName");
			String msgType = requestMap.get("MsgType");
			String content = requestMap.get("Content");

			TextMessage tm = new TextMessage();
			tm.setToUserName(fromUserName);
			tm.setFromUserName(toUserName);
			tm.setMsgType("text");
			tm.setCreateTime(new Date().getDate());

			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				if(content.equals("你好")){
					tm.setContent("你好啊");
				}else if(content.equals("单图文")){
					Article article = new Article();
					article.setTitle("水果知识");
					article.setDescription("欢迎来到尚果商城，这里有一大波水果介绍给你，等你来尝，一大波哦，你懂的！！！");
					System.out.println(request.getScheme()+"://"+request.getServerName()+request.getContextPath()+"/image/yingtao.jpg");
					article.setPicUrl(request.getScheme()+"://"+request.getServerName()+request.getContextPath()+"/image/yingtao.jpg");
					article.setUrl("http://www.fruitday.com/");
					List<Article> articles = new ArrayList<Article>();
					articles.add(article);
					NewsMessage newsMessage = new NewsMessage();
					newsMessage.setFromUserName(toUserName);
					newsMessage.setToUserName(fromUserName);
					newsMessage.setCreateTime(new Date().getTime());
					newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
					newsMessage.setArticleCount(1);
					newsMessage.setArticles(articles);
					
					respXml = MessageUtil.messageToXml(newsMessage);
					return respXml;
				}else if(content.equals("多图文")){
					Article article = new Article();
					article.setTitle("水果知识");
					article.setDescription("欢迎来到尚果商城，这里有一大波水果介绍给你，等你来尝，一大波哦，你懂的！！！");
					article.setPicUrl(request.getScheme()+"://"+request.getServerName()+request.getContextPath()+"/image/yingtao.jpg");
					article.setUrl("http://www.fruitday.com/");
					
					Article article1 = new Article();
					article1.setTitle("盛夏冰果");
					article1.setDescription("冰果透心凉，等你来尝，一大波哦，你懂的！！！");
					article1.setPicUrl(request.getScheme()+"://"+request.getServerName()+request.getContextPath()+"/image/yingtao.jpg");
					article1.setUrl("http://www.fruitday.com/");
					
					Article article2 = new Article();
					article2.setTitle("水果营养");
					article2.setDescription("欢迎来到尚果商城，这里有一大波水果介绍给你，等你来尝，一大波哦，你懂的！！！");
					article2.setPicUrl(request.getScheme()+"://"+request.getServerName()+request.getContextPath()+"/image/yingtao.jpg");
					article2.setUrl("http://www.fruitday.com/");
					
					List<Article> articles = new ArrayList<Article>();
					articles.add(article);
					articles.add(article1);
					articles.add(article2);
					NewsMessage newsMessage = new NewsMessage();
					newsMessage.setFromUserName(toUserName);
					newsMessage.setToUserName(fromUserName);
					newsMessage.setCreateTime(new Date().getTime());
					newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
					newsMessage.setArticleCount(3);
					newsMessage.setArticles(articles);
					
					respXml = MessageUtil.messageToXml(newsMessage);
					return respXml;
				}else{
					tm.setContent("你发送的是文本消息");
				}
				
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				System.out.println("你发送的是图片消息");
				tm.setContent("你发送的是图片消息");
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				System.out.println("你发送的是语音消息");
				tm.setContent("你发送的是语音消息");
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				tm.setContent("你发送的是视频消息");
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				tm.setContent("你发送的是位置消息");
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
				tm.setContent("你发送的是小视频消息");
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				tm.setContent("你发送的是链接消息");
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				String eventType = requestMap.get("Event");
				if(eventType.equals(MessageUtil.REQ_EVENT_TYPE_SUBSCRIBE)){
					tm.setContent("欢饮您关注，我们将用心为您服务");
				}else if(eventType.equals(MessageUtil.REQ_EVENT_TYPE_UNSUBSCRIBE)){
					
				}
			}
			respXml = MessageUtil.messageToXml(tm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(respXml);
		return respXml;
	}
}


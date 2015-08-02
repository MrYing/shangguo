package com.shangguo.service.user;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.shangguo.dao.base.QueryResult;
import com.shangguo.dao.user.impl.UserDao;
import com.shangguo.model.user.T_user;
import com.shangguo.util.DateUtil;
import com.shangguo.weixin.util.CommonUtil;

public class UserInfoService {
	public static Logger log = Logger.getLogger(UserInfoService.class);
	UserDao userDao = new UserDao();
	
	/**
	 * 获取微信用户信息，写入到数据库
	 */
	public boolean saveUserBaseInfo(String openID){
		
		boolean isOpenId = userDao.isExistsOpenId(openID);
		StringBuffer address = new StringBuffer();
		if(isOpenId){
			log.info("该用户已存在，之前已经关注过" + ",  openID :" + openID);
			return isOpenId;
		}
		T_user user = new T_user();
		String accessToken = CommonUtil.getAccessToken();
		JSONObject userInfoJSONObject = CommonUtil.getUserBaseInfo(accessToken, openID);
		//user.setAddress((String) userInfoJSONObject.get(""));
		user.setCreat_time(DateUtil.getDateTime());
		int gender = (Integer) userInfoJSONObject.get("sex");
		if(gender == 1){
			user.setGender("男");
		}else if(gender == 2){
			user.setGender("女");
		}else{
			user.setGender("未知性别");
		}
		user.setIcon_url((String) userInfoJSONObject.get("headimgurl"));
		user.setJoin_time(DateUtil.timestamp2Date(userInfoJSONObject.getString("subscribe_time")));
		user.setOpenID((String) userInfoJSONObject.get("openid"));
		user.setUnionID((String) userInfoJSONObject.get("unionid"));
		user.setUser_nickname((String) userInfoJSONObject.get("nickname"));
		//获取用户地址信息
		String country = userInfoJSONObject.getString("country");
		String province = userInfoJSONObject.getString("province");
		String city = userInfoJSONObject.getString("city");
		if(country !=null){
			address.append(country).append("·");
		}
		if(province !=null){
			address.append(province).append("·");
		}
		if(city !=null){
			address.append(city);
		}
		user.setAddress(address.toString());
		new UserDao().save(user);
		
		return isOpenId;
	}
	
	/**
	 * 查询用户信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public QueryResult<T_user> queryUserInfo(int pageNo , int pageSize){
		
		return userDao.findByPage(pageNo, pageSize);
		
	}
	public static void main(String[] args) {
		//new UserBaseInfo().queryUserInfo();
	}
}

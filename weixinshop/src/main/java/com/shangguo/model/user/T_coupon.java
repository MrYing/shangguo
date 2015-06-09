package com.shangguo.model.user;

import java.io.Serializable;
import java.util.Date;

/**
 * t_coupon 实体类 Tue Jun 09 22:01:30 CST 2015 JasonLin
 */

public class T_coupon implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 544604038899171883L;
	private int coupon_id;
	private int user_id;
	private String type;
	private Date introduce;
	private String status;
	private Date creat_time;

	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}

	public int getCoupon_id() {
		return coupon_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setIntroduce(Date introduce) {
		this.introduce = introduce;
	}

	public Date getIntroduce() {
		return introduce;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setCreat_time(Date creat_time) {
		this.creat_time = creat_time;
	}

	public Date getCreat_time() {
		return creat_time;
	}
}

package com.shangguo.model.user;

import java.io.Serializable;
import java.util.Date;

/**
 * t_user 实体类 Tue Jun 09 21:56:58 CST 2015 JasonLin
 */

public class T_user implements Serializable {
	private static final long serialVersionUID = -1088445863323267490L;  
	private int user_id;
	private String user_name;
	private Date join_time;
	private String user_nickname;
	private String phone;
	private String address;
	private String gender;
	private int score;
	private String icon_url;
	private Date creat_time;

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setJoin_time(Date join_time) {
		this.join_time = join_time;
	}

	public Date getJoin_time() {
		return join_time;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setCreat_time(Date creat_time) {
		this.creat_time = creat_time;
	}

	public Date getCreat_time() {
		return creat_time;
	}
}

package com.shangguo.model.systemadmin;

import java.io.Serializable;
import java.util.Date;

/**
 * t_admin 实体类 Tue Jun 09 23:02:21 CST 2015 JasonLin
 */

public class T_admin implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7442730805689050327L;
	private int admin_id;
	private String login_account;
	private String name;
	private String password;
	private String status;
	private Date creat_time;

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setLogin_account(String login_account) {
		this.login_account = login_account;
	}

	public String getLogin_account() {
		return login_account;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
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

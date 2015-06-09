package com.shangguo.model.user;

import java.io.Serializable;
import java.util.Date;

/**
 * t_address 实体类 Tue Jun 09 22:00:50 CST 2015 JasonLin
 */

public class T_address implements Serializable {

	private static final long serialVersionUID = 4668084533292609643L;
	private int adress_id;
	private int user_id;
	private int address_order;
	private String address;
	private String phone;
	private String receiver;
	private Date delivery_time;
	private Date creat_time;
	private String status;

	public void setAdress_id(int adress_id) {
		this.adress_id = adress_id;
	}

	public int getAdress_id() {
		return adress_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setAddress_order(int address_order) {
		this.address_order = address_order;
	}

	public int getAddress_order() {
		return address_order;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setDelivery_time(Date delivery_time) {
		this.delivery_time = delivery_time;
	}

	public Date getDelivery_time() {
		return delivery_time;
	}

	public void setCreat_time(Date creat_time) {
		this.creat_time = creat_time;
	}

	public Date getCreat_time() {
		return creat_time;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}

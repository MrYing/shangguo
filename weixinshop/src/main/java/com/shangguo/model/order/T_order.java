package com.shangguo.model.order;

import java.util.Date;
import java.io.Serializable;

/**
 * t_order 实体类 Tue Jun 09 22:56:26 CST 2015 JasonLin
 */

public class T_order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1487940224622928934L;
	private int order_id;
	private int order_type;
	private int user_id;
	private int order_status;
	private Date order_time;
	private int pay_type;
	private int carry_type;
	private String address;
	private String phone;
	private String receiver;
	private Date delivery_time;
	private double monny;
	private int activity_type;
	private String remark;

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_type(int order_type) {
		this.order_type = order_type;
	}

	public int getOrder_type() {
		return order_type;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}

	public int getOrder_status() {
		return order_status;
	}

	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}

	public Date getOrder_time() {
		return order_time;
	}

	public void setPay_type(int pay_type) {
		this.pay_type = pay_type;
	}

	public int getPay_type() {
		return pay_type;
	}

	public void setCarry_type(int carry_type) {
		this.carry_type = carry_type;
	}

	public int getCarry_type() {
		return carry_type;
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

	public void setMonny(double monny) {
		this.monny = monny;
	}

	public double getMonny() {
		return monny;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public int getActivity_type() {
		return activity_type;
	}

	public void setActivity_type(int activity_type) {
		this.activity_type = activity_type;
	}
}

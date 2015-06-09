package com.shangguo.model.order;

import java.io.Serializable;

/**
 * t_orderlist 实体类 Tue Jun 09 22:56:48 CST 2015 JasonLin
 */

public class T_orderlist implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8134027564350123479L;
	private int order_id;
	private int good_id;
	private double good_name;
	private double original_price;
	private double present_price;
	private double order_price;
	private double buy_count;
	private double money;

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setGood_id(int good_id) {
		this.good_id = good_id;
	}

	public int getGood_id() {
		return good_id;
	}

	public void setGood_name(double good_name) {
		this.good_name = good_name;
	}

	public double getGood_name() {
		return good_name;
	}

	public void setOriginal_price(double original_price) {
		this.original_price = original_price;
	}

	public double getOriginal_price() {
		return original_price;
	}

	public void setPresent_price(double present_price) {
		this.present_price = present_price;
	}

	public double getPresent_price() {
		return present_price;
	}

	public void setOrder_price(double order_price) {
		this.order_price = order_price;
	}

	public double getOrder_price() {
		return order_price;
	}

	public void setBuy_count(double buy_count) {
		this.buy_count = buy_count;
	}

	public double getBuy_count() {
		return buy_count;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getMoney() {
		return money;
	}
}

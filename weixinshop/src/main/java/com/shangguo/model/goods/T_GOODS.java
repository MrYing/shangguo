package com.shangguo.model.goods;

import java.io.Serializable;
import java.util.Date;

/**
 * T_GOODS 实体类 Tue Jun 09 22:06:30 CST 2015 JasonLin
 */

public class T_GOODS implements Serializable {

	private static final long serialVersionUID = 2166319205766057857L;
	private int goods_id;
	private int gategory_id;
	private String goods_name;
	private int goods_order;
	private double original_price;
	private double present_price;
	private double order_price;
	private double amount;
	private String status;
	private double inventory;
	private String introduce;
	private String image_url;
	private Date creat_time;
	private String product_text;
	private int times;

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGategory_id(int gategory_id) {
		this.gategory_id = gategory_id;
	}

	public int getGategory_id() {
		return gategory_id;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_order(int goods_order) {
		this.goods_order = goods_order;
	}

	public int getGoods_order() {
		return goods_order;
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

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setInventory(double inventory) {
		this.inventory = inventory;
	}

	public double getInventory() {
		return inventory;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setCreat_time(Date creat_time) {
		this.creat_time = creat_time;
	}

	public Date getCreat_time() {
		return creat_time;
	}

	public void setProduct_text(String product_text) {
		this.product_text = product_text;
	}

	public String getProduct_text() {
		return product_text;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getTimes() {
		return times;
	}
}

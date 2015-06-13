package com.shangguo.model.shoppingcart;

/**
 * t_shoppingcart 实体类 Wed Jun 10 22:25:38 CST 2015 JasonLin
 */

public class T_shoppingcart {
	private int goods_id;
	private double buy_count;
	private int user_id;

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setBuy_count(double buy_count) {
		this.buy_count = buy_count;
	}

	public double getBuy_count() {
		return buy_count;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getUser_id() {
		return user_id;
	}
}

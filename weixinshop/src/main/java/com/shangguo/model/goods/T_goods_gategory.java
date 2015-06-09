package com.shangguo.model.goods;

import java.io.Serializable;
import java.util.Date;

/**
 * t_goods_gategory 实体类 Tue Jun 09 22:06:41 CST 2015 JasonLin
 */

public class T_goods_gategory implements Serializable {

	private static final long serialVersionUID = 1127485481378018214L;
	private int gategory_id;
	private String name;
	private String introduce;
	private Date creat_time;

	public void setGategory_id(int gategory_id) {
		this.gategory_id = gategory_id;
	}

	public int getGategory_id() {
		return gategory_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setCreat_time(Date creat_time) {
		this.creat_time = creat_time;
	}

	public Date getCreat_time() {
		return creat_time;
	}
}

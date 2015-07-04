package com.shangguo.dao.shoppingcart.impl;

import java.util.ArrayList;

import com.shangguo.dao.base.BaseDaoImpl;
import com.shangguo.dao.shoppingcart.IShoppingcartDao;
import com.shangguo.model.shoppingcart.T_shoppingcart;

public class ShoppingcartDao extends BaseDaoImpl<T_shoppingcart> implements
		IShoppingcartDao {/*
	public int save(T_shoppingcart entity) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into T_shoppingcart set user_id = ? , goods_id=? ,buy_count=? ");
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(entity.getUser_id());
		param.add(entity.getGoods_id());
		param.add(entity.getBuy_count());

		return super.query(sql.toString(), param);

	}

	public int update(T_shoppingcart entity) {
		StringBuffer sql = new StringBuffer();
		sql.append("update set  T_shoppingcart set buy_count=? where user_id = ? and goods_id=? ");
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(entity.getBuy_count());
		param.add(entity.getUser_id());
		param.add(entity.getGoods_id());

		return super.query(sql.toString(), param);
	}

	public int delete(int user_id) {
		StringBuffer sql = new StringBuffer();
		sql.append("delect from T_shoppingcart where user_id = ?");
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(user_id);
		return super.query(sql.toString(), param);
	}

	public int delete(T_shoppingcart entity) {
		StringBuffer sql = new StringBuffer();
		sql.append("delect from T_shoppingcart where user_id = ? and  buy_count=?  and goods_id=?  ");
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(entity.getUser_id());
		param.add(entity.getBuy_count());
		param.add(entity.getGoods_id());
		return super.query(sql.toString(), param);
	}

*/}

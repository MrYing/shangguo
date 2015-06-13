package com.shangguo.dao.shoppingcart.impl;

import com.shangguo.dao.base.BaseDaoImpl;
import com.shangguo.dao.shoppingcart.IShoppingcartDao;
import com.shangguo.model.shoppingcart.T_shoppingcart;

public class ShoppingcartDao extends BaseDaoImpl<T_shoppingcart> implements
		IShoppingcartDao {
	public int save(T_shoppingcart entity) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into T_shoppingcart set user_id = ? , goods_id=? ,buy_count=? ");
		Object[] args = new Object[3];
		args[0] = entity.getUser_id();
		args[1] = entity.getGoods_id();
		args[2] = entity.getBuy_count();
		return super.query(sql.toString(), args);

	}

	public int update(T_shoppingcart entity) {
		StringBuffer sql = new StringBuffer();
		sql.append("update set  T_shoppingcart set buy_count=? where user_id = ? and goods_id=? ");
		Object[] args = new Object[3];
		args[0] = entity.getBuy_count();
		args[1] = entity.getUser_id();
		args[2] = entity.getGoods_id();
		return super.query(sql.toString(), args);
	}

	public int delete(int user_id) {
		StringBuffer sql = new StringBuffer();
		sql.append("delect from T_shoppingcart where user_id = ?");
		int[] args = new int[1];
		args[0] = user_id;
		return super.query(sql.toString(), args);
	}

	public int delete(T_shoppingcart entity) {
		StringBuffer sql = new StringBuffer();
		sql.append("delect from T_shoppingcart where user_id = ? and  buy_count=?  and goods_id=?  ");
		Object[] args = new Object[3];
		args[0] = entity.getUser_id();
		args[1] = entity.getBuy_count();
		args[2] = entity.getGoods_id();
		return super.query(sql.toString(), args);
	}

}

package com.shangguo.dao.order.impl;

import java.util.ArrayList;

import com.shangguo.dao.base.BaseDaoImpl;
import com.shangguo.dao.base.QueryResult;
import com.shangguo.dao.order.IOrderListDao;
import com.shangguo.model.order.T_orderlist;

public class OrderListDao extends BaseDaoImpl<T_orderlist> implements
		IOrderListDao {

	public int save(T_orderlist entity) {
		ArrayList<Object> param = new ArrayList<Object>();
		String sql = "insert into t_orderlist (order_id, good_id, good_name, original_price, "
				+ "present_price, order_price, buy_count, money) values(?,?,?,?,?,?,?,?)";
		param.add(entity.getOrder_id());
		param.add(entity.getGood_id());
		param.add(entity.getGood_name());
		param.add(entity.getOriginal_price());
		param.add(entity.getPresent_price());
		param.add(entity.getOrder_price());
		param.add(entity.getBuy_count());
		param.add(entity.getMoney());
		return super.modify(sql, param);

	}

	public int update(T_orderlist entity) {
		ArrayList<Object> param = new ArrayList<Object>();
		String sql = "update t_orderlist set order_id=?, good_id=?, good_name=?, original_price=?, "
				+ "present_price=?, order_price=?, buy_count=?, money=? where order_id=? and good_id=?";
		param.add(entity.getOrder_id());
		param.add(entity.getGood_id());
		param.add(entity.getGood_name());
		param.add(entity.getOriginal_price());
		param.add(entity.getPresent_price());
		param.add(entity.getOrder_price());
		param.add(entity.getBuy_count());
		param.add(entity.getMoney());
		param.add(entity.getOrder_id());
		param.add(entity.getGood_id());
		return super.modify(sql, param);
	}

	public int delete(T_orderlist entity) {
		ArrayList<Object> param = new ArrayList<Object>();
		String sql = "delete * from t_orderlist where order_id=? and good_id=?";
		param.add(entity.getOrder_id());
		param.add(entity.getGood_id());
		return super.modify(sql, param);
	}

	public QueryResult<T_orderlist> findByOrderId(int orderId) {
		ArrayList<Object> param = new ArrayList<Object>();
		String sql = "select  * from t_orderlist where order_id=?";
		param.add(orderId);
		return super.findByPage(0, 0, sql, param);
	}

	public QueryResult<T_orderlist> findByOrderIds(int[] orderId) {
		ArrayList<Object> param = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(
				"select  * from t_orderlist where order_id in (");
		for (int id : orderId) {
			sql.append("?,");
		}
		sql = sql.deleteCharAt(sql.length() - 1);
		sql.append(") ");

		int length = orderId.length;
		for (int i = 0; i < length; i++) {
			param.add(orderId[i]);
		}

		return super.findByPage(0, 0, sql.toString(), param);
	}
}

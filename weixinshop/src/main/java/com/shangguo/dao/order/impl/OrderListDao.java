package com.shangguo.dao.order.impl;

import java.util.ArrayList;

import com.shangguo.dao.base.BaseDaoImpl;
import com.shangguo.dao.base.QueryResult;
import com.shangguo.dao.order.IOrderListDao;
import com.shangguo.model.goods.T_GOODS;
import com.shangguo.model.order.T_orderlist;

public class OrderListDao extends BaseDaoImpl<T_orderlist> implements
		IOrderListDao {
	String id_name = "";

	public int save(T_orderlist entity) {
		return super.save(entity, id_name);

	}

	public int update(T_orderlist entity) {
		return super.update(entity, id_name);
	}

	public int delete(T_orderlist entity) {
		return super.delete(entity, id_name);
	}

	public QueryResult<T_orderlist> findByPage(int pageNo, int pageSize,
			String findsql, ArrayList<Object> param) {
		return super.findByPage(pageNo, pageSize, findsql, param);
	}
}

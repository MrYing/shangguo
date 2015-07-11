package com.shangguo.service.order;

import java.util.ArrayList;

import com.shangguo.dao.base.QueryResult;
import com.shangguo.dao.goods.impl.GoodsDao;
import com.shangguo.dao.order.impl.OrderDao;
import com.shangguo.model.goods.T_GOODS;
import com.shangguo.model.order.T_order;
import com.shangguo.util.MyUtil;

public class OrderService {

	OrderDao dao = new OrderDao();

	/**
	 * 按名称模糊查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param name
	 * @return
	 */
	public QueryResult<T_order> findBynameByPage(int pageNo, int pageSize,
			int order_id, int user_id) {

		return dao.findBynameByPage(pageNo, pageSize, order_id, user_id);
	}

	/**
	 * 按订单Id查询订单详情
	 * 
	 */
	public QueryResult<T_order> findListByOrderId(int pageNo, int pageSize,
			int order_id, int user_id) {
		StringBuffer sql = new StringBuffer();
		ArrayList<Object> param = new ArrayList<Object>();
		sql.append("select * from t_order ");
		if (order_id != -999) {
			sql.append(" where order_id like ?");
			param.add("%" + order_id + "%");
		}
		if (user_id != -999) {
			sql.append(" where user_id like ?");
			param.add("%" + user_id + "%");
		}
		sql.append(" order by order_id ");
		return dao.findByPage(pageNo, pageSize, sql.toString(), param);
	}
}

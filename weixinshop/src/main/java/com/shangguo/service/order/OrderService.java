package com.shangguo.service.order;

import java.util.ArrayList;

import com.shangguo.dao.base.QueryResult;
import com.shangguo.dao.order.impl.OrderDao;
import com.shangguo.dao.order.impl.OrderListDao;
import com.shangguo.model.order.T_order;
import com.shangguo.model.order.T_orderlist;

public class OrderService {

	OrderDao dao = new OrderDao();
	OrderListDao OrderListDao = new OrderListDao();

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
	public QueryResult<T_orderlist> findListByOrderId(int order_id) {
		return OrderListDao.findByOrderId(order_id);
	}

	/**
	 * 按订单Id查询订单详情
	 * 
	 */
	public ArrayList<Object> findListByOrderId(int[] order_ids) {
		ArrayList<Object> result = new ArrayList<Object>();
		for(int id :order_ids){
			result.add(OrderListDao.findByOrderId(id));
		}
		return result;
	}

	/**
	 * 按订单Id查询订单详情
	 * 
	 */
	public int updateStutas(int status, String[] orderIds) {
		return dao.updateStatus(status, orderIds);
	}
}

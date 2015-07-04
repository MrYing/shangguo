package com.shangguo.dao.order.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.shangguo.dao.base.BaseDaoImpl;
import com.shangguo.dao.base.QueryResult;
import com.shangguo.dao.order.IOrderDao;
import com.shangguo.model.order.T_order;

public class OrderDao extends BaseDaoImpl<T_order> implements IOrderDao {

	private String id_name = "order_id";

	public int save(T_order entity) {
		return super.save(entity, id_name);

	}

	public int update(T_order entity) {
		exists_id(entity.getOrder_id());
		return super.update(entity, id_name);
	}

	public int delete(T_order entity) {
		exists_id(entity.getOrder_id());
		return super.delete(entity, id_name);
	}

	public int delete(int id) {
		exists_id(id);
		return super.delete(id, id_name);
	}

/*	public void batchSave(List<T_order> list) {
		super.batchSave(list);
	}

	public void batchUpdate(List<T_order> list) {
		super.batchUpdate(list);
	}

	public void batchDelete(List<T_order> list) {
		super.batchDelete(list);
	}*/

	public T_order findById(int id) {
		exists_id(id);
		return super.findById(id, id_name);
	}

	public List<T_order> findAll() {
		return super.findAll();
	}

	public QueryResult<T_order> findByPage(int pageNo, int pageSize) {
		return super.findByPage(pageNo, pageSize);
	}

	public QueryResult<T_order> findByPage(int pageNo, int pageSize,
			Map<String, String> where) {
		return super.findByPage(pageNo, pageSize, where);
	}

	public QueryResult<T_order> findByPage(int pageNo, int pageSize,
			LinkedHashMap<String, String> orderby) {
		return super.findByPage(pageNo, pageSize, orderby);
	}

	private void exists_id(int id) {
		try {
			if (id < 1)
				throw new Exception("传入Id为空！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

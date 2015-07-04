package com.shangguo.dao.systemadmin.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.shangguo.dao.base.BaseDaoImpl;
import com.shangguo.dao.base.QueryResult;
import com.shangguo.dao.systemadmin.IAdamin;
import com.shangguo.model.goods.T_GOODS;
import com.shangguo.model.systemadmin.T_admin;

public class AdminDao extends BaseDaoImpl<T_admin> implements IAdamin {

	private String id_name = "admin_id";

	public int save(T_admin entity) {
		return super.save(entity, id_name);

	}

	public int update(T_admin entity) {
		exists_id(entity.getAdmin_id());
		return super.update(entity, id_name);

	}

	public int delete(T_admin entity) {
		exists_id(entity.getAdmin_id());
		return super.delete(entity, id_name);
	}

	public int delete(int id) {
		exists_id(id);
		return super.delete(id, id_name);
	}

/*	public void batchSave(List<T_admin> list) {
		super.batchSave(list);

	}

	public void batchUpdate(List<T_admin> list) {
		super.batchUpdate(list);

	}

	public void batchDelete(List<T_admin> list) {
		super.batchDelete(list);

	}*/

	public T_admin findById(int id) {
		exists_id(id);
		return super.findById(id, id_name);
	}

	public List<T_admin> findAll() {
		return super.findAll();
	}

	public QueryResult<T_admin> findByPage(int pageNo, int pageSize) {
		return super.findByPage(pageNo, pageSize);
	}

	public QueryResult<T_admin> findByPage(int pageNo, int pageSize,
			Map<String, String> where) {
		return super.findByPage(pageNo, pageSize, where);

	}

	public QueryResult<T_admin> findByPage(int pageNo, int pageSize,
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

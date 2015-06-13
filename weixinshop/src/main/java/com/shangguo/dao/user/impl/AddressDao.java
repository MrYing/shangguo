package com.shangguo.dao.user.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.shangguo.dao.base.BaseDaoImpl;
import com.shangguo.dao.base.QueryResult;
import com.shangguo.dao.user.IAddressDao;
import com.shangguo.model.user.T_address;

public class AddressDao extends BaseDaoImpl<T_address> implements IAddressDao {

	private String id_name = "adress_id";

	public int save(T_address entity) {
		return super.save(entity, id_name);

	}

	public int update(T_address entity) {
		exists_id(entity.getAdress_id());
		return super.update(entity, id_name);
	}

	public int delete(T_address entity) {
		exists_id(entity.getAdress_id());
		return super.delete(entity, id_name);
	}

	public int delete(int id) {
		exists_id(id);
		return super.delete(id, id_name);
	}

	public void batchSave(List<T_address> list) {
		super.batchSave(list);
	}

	public void batchUpdate(List<T_address> list) {
		super.batchUpdate(list);
	}

	public void batchDelete(List<T_address> list) {
		super.batchDelete(list);
	}

	public T_address findById(int id) {
		exists_id(id);
		return super.findById(id, id_name);
	}

	public List<T_address> findAll() {
		return super.findAll();
	}

	public QueryResult<T_address> findByPage(int pageNo, int pageSize) {
		return super.findByPage(pageNo, pageSize);
	}

	public QueryResult<T_address> findByPage(int pageNo, int pageSize,
			Map<String, String> where) {
		return super.findByPage(pageNo, pageSize, where);
	}

	public QueryResult<T_address> findByPage(int pageNo, int pageSize,
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

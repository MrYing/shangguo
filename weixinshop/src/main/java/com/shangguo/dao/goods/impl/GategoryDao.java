package com.shangguo.dao.goods.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.shangguo.dao.base.BaseDaoImpl;
import com.shangguo.dao.base.QueryResult;
import com.shangguo.dao.goods.IGategoryDao;
import com.shangguo.model.goods.T_GOODS;
import com.shangguo.model.goods.T_goods_gategory;
import com.shangguo.util.MyUtil;

public class GategoryDao extends BaseDaoImpl<T_goods_gategory> implements
		IGategoryDao {

	private String id_name = "gategory_id";

	public int save(T_goods_gategory entity) {
		return super.save(entity, id_name);

	}

	public int update(T_goods_gategory entity) {
		exists_id(entity.getGategory_id());
		return super.update(entity, id_name);
	}

	public int delete(T_goods_gategory entity) {
		exists_id(entity.getGategory_id());
		return super.delete(entity, id_name);
	}

	public int delete(int id) {
		exists_id(id);
		return super.delete(id, id_name);
	}

	public int[] batchSave(List<T_goods_gategory> list) {
		return super.batchSave(list, id_name);
	}

	public int[] batchUpdate(List<T_goods_gategory> list) {
		return super.batchUpdate(list, id_name);
	}

	public int[] batchDelete(List<T_goods_gategory> list) {
		return super.batchDelete(list, id_name);
	}

	public void batchDeleteById(int[] ids) {
		super.batchDeleteById(ids, id_name);
	}

	public T_goods_gategory findById(int id) {
		exists_id(id);
		return super.findById(id, id_name);
	}

	public List<T_goods_gategory> findAll() {
		return super.findAll();
	}

	public QueryResult<T_goods_gategory> findByPage(int pageNo, int pageSize) {
		return super.findByPage(pageNo, pageSize);
	}

	public QueryResult<T_goods_gategory> findByPage(int pageNo, int pageSize,
			Map<String, String> where) {
		return super.findByPage(pageNo, pageSize, where);
	}

	public QueryResult<T_goods_gategory> findByPage(int pageNo, int pageSize,
			LinkedHashMap<String, String> orderby) {
		return super.findByPage(pageNo, pageSize, orderby);
	}

	public QueryResult<T_goods_gategory> findByPage(int pageNo, int pageSize,
			Map<String, String> where, LinkedHashMap<String, String> orderby) {
		return super.findByPage(pageNo, pageSize, where, orderby);
	}

	public QueryResult<T_goods_gategory> findByPage(int pageNo, int pageSize,
			String findsql, ArrayList<Object> param) {
		return super.findByPage(pageNo, pageSize, findsql, param);
	}

	/**
	 * 按名称模糊查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param name
	 * @return
	 */
	public QueryResult<T_goods_gategory> findBynameByPage(int pageNo,
			int pageSize, String name) {
		StringBuffer sql = new StringBuffer();
		ArrayList<Object> param = new ArrayList<Object>();
		sql.append("select * from t_goods_gategory ");
		if (MyUtil.isNotEmpty(name)) {
			sql.append(" where name like ?");
			param.add("%" + name + "%");
		}
		sql.append(" order by gategory_id ");
		return findByPage(pageNo, pageSize, sql.toString(), param);
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

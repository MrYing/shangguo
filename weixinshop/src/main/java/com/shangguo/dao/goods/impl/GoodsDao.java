package com.shangguo.dao.goods.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shangguo.dao.base.BaseDaoImpl;
import com.shangguo.dao.base.QueryResult;
import com.shangguo.dao.goods.IGoodsDao;
import com.shangguo.model.goods.T_GOODS;
import com.shangguo.util.MyUtil;

@Repository
public class GoodsDao extends BaseDaoImpl<T_GOODS> implements IGoodsDao {

	private String id_name = "goods_id";

	public int save(T_GOODS entity) {
		return super.save(entity, id_name);

	}

	public int update(T_GOODS entity) {
		exists_id(entity.getGoods_id());
		return super.update(entity, id_name);
	}

	public int delete(T_GOODS entity) {
		exists_id(entity.getGoods_id());
		return super.delete(entity, id_name);
	}

	public int delete(int id) {
		exists_id(id);
		return super.delete(id, id_name);
	}

	public int[] batchSave(List<T_GOODS> list) {
		return super.batchSave(list, id_name);
	}

	public int[] batchUpdate(List<T_GOODS> list) {
		return super.batchUpdate(list, id_name);
	}

	public int[] batchDelete(List<T_GOODS> list) {
		return super.batchDelete(list, id_name);
	}

	public void batchDeleteById(int[] ids) {
		super.batchDeleteById(ids, id_name);
	}

	public T_GOODS findById(int id) {
		exists_id(id);
		return super.findById(id, id_name);
	}

	public List<T_GOODS> findAll() {
		return super.findAll();
	}

	public QueryResult<T_GOODS> findByPage(int pageNo, int pageSize) {
		return super.findByPage(pageNo, pageSize);
	}

	public QueryResult<T_GOODS> findByPage(int pageNo, int pageSize,
			Map<String, String> where) {
		return super.findByPage(pageNo, pageSize, where);
	}

	public QueryResult<T_GOODS> findByPage(int pageNo, int pageSize,
			LinkedHashMap<String, String> orderby) {
		return super.findByPage(pageNo, pageSize, orderby);
	}

	public QueryResult<T_GOODS> findByPage(int pageNo, int pageSize,
			Map<String, String> where, LinkedHashMap<String, String> orderby) {
		return super.findByPage(pageNo, pageSize, where, orderby);
	}

	public QueryResult<T_GOODS> findByPage(int pageNo, int pageSize,
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
	public QueryResult<T_GOODS> findBynameByPage(int pageNo, int pageSize,
			String name) {
		StringBuffer sql = new StringBuffer();
		ArrayList<Object> param = new ArrayList<Object>();
		sql.append("select * from t_goods ");
		if (MyUtil.isNotEmpty(name)) {
			sql.append(" where goods_name like ?");
			param.add("%" + name + "%");
		}
		sql.append(" order by goods_order ");
		return findByPage(pageNo, pageSize, sql.toString(), param);
	}

	/**
	 * 按Gategoryid查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param name
	 * @return
	 */
	public List<T_GOODS> findByGategoryid(int[] ids) {
		StringBuffer sql = new StringBuffer();
		ArrayList<Object> param = new ArrayList<Object>();
		sql.append("select * from t_goods  where gategory_id in ( ");
		for (Object id : ids) {
			sql.append("?,");
			param.add(id);
		}
		sql = sql.deleteCharAt(sql.length() - 1);
		sql.append(") ");

		return query(sql.toString(), param);
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

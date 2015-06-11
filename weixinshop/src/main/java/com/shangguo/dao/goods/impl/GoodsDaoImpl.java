package com.shangguo.dao.goods.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.shangguo.dao.base.BaseDaoImpl;
import com.shangguo.dao.base.QueryResult;
import com.shangguo.dao.goods.GoodsDao;
import com.shangguo.model.goods.T_GOODS;

public class GoodsDaoImpl extends BaseDaoImpl<T_GOODS>  implements GoodsDao {
	
	private String id_name="goods_id";
	
//	@Override
	public int save(T_GOODS entity) {
		return super.save(entity,id_name);

	}

//	@Override
	public int update(T_GOODS entity) {
		exists_id(entity.getGoods_id());
		return super.update(entity, id_name);
	}

//	@Override
	public int delete(T_GOODS entity) {
		exists_id(entity.getGoods_id());
		return super.delete(entity, id_name);
	}

//	@Override
	public int delete(int id) {
		exists_id(id);
		return super.delete(id, id_name);
	}

//	@Override
	public void batchSave(List<T_GOODS> list) {
		 super.batchSave(list);
	}

//	@Override
	public void batchUpdate(List<T_GOODS> list) {
		super.batchUpdate(list);
	}

//	@Override
	public void batchDelete(List<T_GOODS> list) {
		super.batchDelete(list);
	}

//	@Override
	public T_GOODS findById(Serializable id, String id_name) {
		return super.findById(id, id_name);
	}

//	@Override
	public List<T_GOODS> findAll() {
		return super.findAll();
	}

//	@Override
	public QueryResult<T_GOODS> findByPage(int pageNo, int pageSize) {
		return super.findByPage(pageNo, pageSize);
	}

//	@Override
	public QueryResult<T_GOODS> findByPage(int pageNo, int pageSize,
			Map<String, String> where) {
		return super.findByPage(pageNo, pageSize, where);
	}

//	@Override
	public QueryResult<T_GOODS> findByPage(int pageNo, int pageSize,
			LinkedHashMap<String, String> orderby) {
		return super.findByPage(pageNo, pageSize, orderby);
	}
	
	/*
	 * id_name为空抛出异常
	 */
	private void exists_id(int id) {
		
		try {
			if (id < 1)
				throw new Exception("传入Id为空！");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}

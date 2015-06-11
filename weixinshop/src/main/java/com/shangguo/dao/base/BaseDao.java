package com.shangguo.dao.base;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author lzc
 * @param <T>
 */
public interface BaseDao<T> {

	int save(T entity, String id_name);

	int update(T entity, String id_name);

	int delete(T entity, String id_name);

	int delete(int id, String id_name);

	void batchSave(List<T> list);

	void batchUpdate(List<T> list);

	void batchDelete(List<T> list);

	T findById(Serializable id, String id_name);

	List<T> findAll();

	QueryResult<T> findByPage(int pageNo, int pageSize);

	QueryResult<T> findByPage(int pageNo, int pageSize,
			Map<String, String> where);

	QueryResult<T> findByPage(int pageNo, int pageSize,
			LinkedHashMap<String, String> orderby);

}

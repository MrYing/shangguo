package com.shangguo.dao.base;

import java.util.ArrayList;
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

	int[] batchSave(List<T> list, String id_name);

	int[] batchUpdate(List<T> list, String id_name);

	int[] batchDelete(List<T> list, String id_name);

	void batchDeleteById(int[] ids, String id_name);

	T findById(int id, String id_name);

	List<T> query(String sql, ArrayList<Object> param);

	List<T> findAll();

	QueryResult<T> findByPage(int pageNo, int pageSize);

	QueryResult<T> findByPage(int pageNo, int pageSize,
			Map<String, String> where);

	QueryResult<T> findByPage(int pageNo, int pageSize,
			LinkedHashMap<String, String> orderby);

}

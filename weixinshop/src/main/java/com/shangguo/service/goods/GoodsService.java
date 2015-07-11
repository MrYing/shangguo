package com.shangguo.service.goods;

import java.util.LinkedHashMap;
import java.util.List;

import com.shangguo.dao.base.QueryResult;
import com.shangguo.dao.goods.impl.GoodsDao;
import com.shangguo.model.goods.T_GOODS;

public class GoodsService {
	GoodsDao dao = new GoodsDao();

	public List<T_GOODS> getall() {
		return dao.findAll();
	}

	/**
	 * 保存
	 */
	public int save(T_GOODS entity) {
		return dao.save(entity);
	}

	/**
	 * 更新
	 */
	public int update(T_GOODS entity) {
		return dao.update(entity);
	}

	/**
	 * 批量删除
	 */
	public int[] batchDelete(List<T_GOODS> list) {
		return dao.batchDelete(list);
	}

	public void batchDeleteById(int[] ids) {
		dao.batchDeleteById(ids);
	}

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 *            当前页
	 * @param pageSize
	 *            每页数量
	 * @param orderby
	 *            排序
	 * @return
	 */
	public QueryResult<T_GOODS> findByPage(int pageNo, int pageSize,
			LinkedHashMap<String, String> orderby) {
		return dao.findByPage(pageNo, pageSize, orderby);
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
		return dao.findBynameByPage(pageNo, pageSize, name);
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
		return dao.findByGategoryid(ids);
	}

}

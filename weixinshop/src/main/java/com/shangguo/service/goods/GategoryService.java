package com.shangguo.service.goods;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.shangguo.dao.base.QueryResult;
import com.shangguo.dao.goods.impl.GategoryDao;
import com.shangguo.model.goods.T_goods_gategory;
import com.shangguo.util.MyUtil;

public class GategoryService {

	GategoryDao dao = new GategoryDao();

	public List<T_goods_gategory> getall() {
		return dao.findAll();
	}

	/**
	 * 保存
	 */
	public int save(T_goods_gategory entity) {
		return dao.save(entity);
	}

	/**
	 * 更新
	 */
	public int update(T_goods_gategory entity) {
		return dao.update(entity);
	}

	/**
	 * 批量删除
	 */
	public int[] batchDelete(List<T_goods_gategory> list) {
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
	public QueryResult<T_goods_gategory> findByPage(int pageNo, int pageSize,
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
		return dao.findByPage(pageNo, pageSize, sql.toString(), param);
	}

}

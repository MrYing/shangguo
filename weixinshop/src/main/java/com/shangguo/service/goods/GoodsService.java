package com.shangguo.service.goods;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.shangguo.dao.base.QueryResult;
import com.shangguo.dao.goods.impl.GoodsDao;
import com.shangguo.model.goods.T_GOODS;
import com.shangguo.util.MyUtil;

public class GoodsService {
	GoodsDao dao = new GoodsDao();

	public List<T_GOODS> getall() {
		return dao.findAll();
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
		StringBuffer sql = new StringBuffer();
		ArrayList<Object> param = new ArrayList<Object>();
		sql.append("select * from t_goods ");
		if (MyUtil.isNotEmpty(name)) {
			sql.append(" where goods_name like ?");
			param.add("%" + name + "%");
		}
		sql.append(" order by goods_order ");
		return dao.findByPage(pageNo, pageSize, sql.toString(), param);
	}

}

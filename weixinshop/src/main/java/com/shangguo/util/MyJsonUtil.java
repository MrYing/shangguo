package com.shangguo.util;

import java.util.List;

import com.shangguo.dao.base.QueryResult;

import net.sf.json.JSONArray;

/**
 * 根据easyUi的datagrid的Json格式返回Json串
 * 
 * @author lzc
 * 
 * @param <T>
 */
public class MyJsonUtil<T> {

	/**
	 * 无分页情况,List转easyUi的json
	 * 
	 * @param list
	 * @return JsonString
	 */
	public String toJson(List<T> list) {
		StringBuilder jsonString = new StringBuilder();
		jsonString.append("{ \"total\":").append(list.size())
				.append(", \"rows\":");
		JSONArray jsonarray = JSONArray.fromObject(list);
		jsonString.append(jsonarray.toString()).append("}");
		return jsonString.toString();
	}

	/**
	 * 分页情况,List转easyUi的json
	 * 
	 * @param QueryResult
	 * @return JsonString
	 */
	public String QueryResulttoJson(QueryResult<T> queryResult) {
		StringBuilder jsonString = new StringBuilder();
		jsonString.append("{ \"total\":").append(queryResult.getTotalRow())
				.append(", \"rows\":");
		JSONArray jsonarray = JSONArray.fromObject(queryResult.getList());
		jsonString.append(jsonarray.toString()).append("}");
		return jsonString.toString();
	}

}

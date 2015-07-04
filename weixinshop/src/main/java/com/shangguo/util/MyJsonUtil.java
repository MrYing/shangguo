package com.shangguo.util;

import java.text.DecimalFormat;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import com.shangguo.dao.base.QueryResult;

/**
 * 根据easyUi的datagrid的Json格式返回Json串
 * 
 * @author lzc
 * 
 * @param <T>
 */
public class MyJsonUtil {
	private static JsonConfig jsonConfig = new JsonConfig();

	private MyJsonUtil() {

	}

	/**
	 * 无分页情况,List转easyUi的json
	 * 
	 * @param list
	 * @return JsonString
	 */
	@SuppressWarnings("rawtypes")
	public static String toJson(Object object) {
		jsonConfig.registerJsonValueProcessor(Double.class,
				new JsonValueProcessor() {
					/**
					 * paramString -> 参数名 paramObject -> 参数值
					 */
					@Override
					public Object processObjectValue(String paramString,
							Object paramObject, JsonConfig paramJsonConfig) {
						if (paramObject == null) {
							return 0.00;
						}
						DecimalFormat format = new DecimalFormat("#.00");
						String sMoney = format.format(paramObject);
						return sMoney;
					}

					@Override
					public Object processArrayValue(Object paramObject,
							JsonConfig paramJsonConfig) {
						return paramObject;
					}
				});
		JSONArray jsonarray = JSONArray.fromObject(object, jsonConfig);
		return jsonarray.toString();
	}

	/**
	 * 无分页情况,List转easyUi的json
	 * 
	 * @param list
	 * @return JsonString
	 */
	@SuppressWarnings("rawtypes")
	public static String toGridJson(List list) {
		jsonConfig.registerJsonValueProcessor(Double.class,
				new JsonValueProcessor() {
					/**
					 * paramString -> 参数名 paramObject -> 参数值
					 */
					@Override
					public Object processObjectValue(String paramString,
							Object paramObject, JsonConfig paramJsonConfig) {
						if (paramObject == null) {
							return 0.00;
						}
						DecimalFormat format = new DecimalFormat("#.00");
						String sMoney = format.format(paramObject);
						return sMoney;
					}

					@Override
					public Object processArrayValue(Object paramObject,
							JsonConfig paramJsonConfig) {
						return paramObject;
					}
				});
		StringBuilder jsonString = new StringBuilder();
		jsonString.append("{ \"total\":").append(list.size())
				.append(", \"rows\":");
		JSONArray jsonarray = JSONArray.fromObject(list, jsonConfig);
		jsonString.append(jsonarray.toString()).append("}");
		return jsonString.toString();
	}

	/**
	 * 分页情况,List转easyUi的json
	 * 
	 * @param <T>
	 * 
	 * @param QueryResult
	 * @return JsonString
	 */
	public static <T> String QueryResulttoJson(QueryResult<T> queryResult) {
		jsonConfig.registerJsonValueProcessor(Double.class,
				new JsonValueProcessor() {
					/**
					 * paramString -> 参数名 paramObject -> 参数值
					 */
					@Override
					public Object processObjectValue(String paramString,
							Object paramObject, JsonConfig paramJsonConfig) {
						if (paramObject == null) {
							return 0.00;
						}
						DecimalFormat format = new DecimalFormat("#.00");
						String sMoney = format.format(paramObject);
						return sMoney;
					}

					@Override
					public Object processArrayValue(Object paramObject,
							JsonConfig paramJsonConfig) {
						return paramObject;
					}
				});

		StringBuilder jsonString = new StringBuilder();
		jsonString.append("{ \"total\":").append(queryResult.getTotalRow())
				.append(", \"rows\":");
		JSONArray jsonarray = JSONArray.fromObject(queryResult.getList(),
				jsonConfig);
		jsonString.append(jsonarray.toString()).append("}");
		return jsonString.toString();
	}

	/*
	 * public static String toJsonByHead(Object object, String head) {
	 * StringBuilder jsonString = new StringBuilder(); jsonString.append(" \"" +
	 * head + "\":"); JSONArray jsonarray = JSONArray.fromObject(object);
	 * jsonString.append(jsonarray.toString()).append("}"); return
	 * jsonString.toString(); }
	 */
}

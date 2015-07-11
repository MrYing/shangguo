package com.shangguo.dao.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * 
 */

public class BaseDaoImpl<T> implements BaseDao<T> {

	/** 设置一些操作的常量 */
	public static final String SQL_INSERT = "insert";
	public static final String SQL_UPDATE = "update";
	public static final String SQL_DELETE = "delete";

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/wxshop?useUnicode=true&characterEncoding=utf8");
		ds.setUsername("root");
		ds.setPassword("root");
		jdbcTemplate.setDataSource(ds);
		System.out.println(getClass().toString());
		ParameterizedType type = (ParameterizedType) getClass()
				.getGenericSuperclass();
		System.out.println(type.getActualTypeArguments()[0].toString());
		// System.out.println(type.getActualTypeArguments()[0].toString().getSimpleName());

		entityClass = (Class<T>) type.getActualTypeArguments()[0];
		System.out.println("Dao实现类是：" + entityClass.getName());
	}

	/*
	 * id_name为空抛出异常
	 */
	private void exists_id_name(String id_name) {
		if (StringUtils.isEmpty(id_name))
			try {
				throw new Exception("传入Id名称为空！");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * 根据传入单个实体保存
	 */
	public int save(T entity, String id_name) {
//		exists_id_name(id_name);
		System.out.println("++++save+++++");
		String sql = this.makeSql(SQL_INSERT, id_name);
		System.out.println(sql);
		Object[] args = this.setArgs(entity, SQL_INSERT, id_name);
		// int[] argTypes = this.setArgTypes(entity, SQL_INSERT);

		return jdbcTemplate.update(sql.toString(), args);
	}

	/**
	 * 根据传入单个实体更新
	 */
	public int update(T entity, String id_name) {
		System.out.println("++++update+++++");
		exists_id_name(id_name);
		String sql = this.makeSql(SQL_UPDATE, id_name);
		System.out.println(sql);
		Object[] args = this.setArgs(entity, SQL_UPDATE, id_name);
		// int[] argTypes = this.setArgTypes(entity, SQL_UPDATE);
		return jdbcTemplate.update(sql, args);
	}

	/**
	 * 根据传入单个实体删除
	 */
	public int delete(T entity, String id_name) {
		System.out.println("++++delete+++++");
		exists_id_name(id_name);
		String sql = this.makeSql(SQL_DELETE, id_name);
		System.out.println(sql);
		Object[] args = this.setArgs(entity, SQL_DELETE, id_name);
		// int[] argTypes = this.setArgTypes(entity, SQL_DELETE);
		return jdbcTemplate.update(sql, args);
	}

	/**
	 * 根据传入单个Id删除
	 */
	public int delete(int id, String id_name) {
		exists_id_name(id_name);
		String sql = " DELETE FROM " + entityClass.getSimpleName() + " WHERE "
				+ id_name + "=?";
		return jdbcTemplate.update(sql, id);
	}

	/**
	 * 根据传入的sql和参数信息，执行语句
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<T> query(String sql, ArrayList<Object> param) {
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
		return jdbcTemplate.query(sql.toString(), param.toArray(), rowMapper);
	}

	/**
	 * 批量保存
	 * 
	 * @return
	 */
	public int[] batchSave(List<T> list, String id_name) {
		exists_id_name(id_name);
		System.out.println("++++batchsave+++++");
		String sql = this.makeSql(SQL_INSERT, id_name);
		System.out.println(sql);
		List<Object[]> argsList = new ArrayList<Object[]>();
		for (T entity : list) {
			Object[] args = this.setArgs(entity, SQL_INSERT, id_name);
			argsList.add(args);
		}
		// int[] argTypes = this.setArgTypes(entity, SQL_INSERT);

		return jdbcTemplate.batchUpdate(sql.toString(), argsList);
	}

	/**
	 * 批量更新
	 */
	public int[] batchUpdate(List<T> list, String id_name) {
		System.out.println("++++batchupdate+++++");
		exists_id_name(id_name);
		String sql = this.makeSql(SQL_UPDATE, id_name);
		System.out.println(sql);
		List<Object[]> argsList = new ArrayList<Object[]>();
		for (T entity : list) {
			Object[] args = this.setArgs(entity, SQL_UPDATE, id_name);
			argsList.add(args);
		}
		// int[] argTypes = this.setArgTypes(entity, SQL_UPDATE);
		return jdbcTemplate.batchUpdate(sql, argsList);
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 * 
	 */
	public int[] batchDelete(List<T> list, String id_name) {
		System.out.println("++++batchdelete+++++");
		exists_id_name(id_name);
		String sql = this.makeSql(SQL_DELETE, id_name);
		System.out.println(sql);
		List<Object[]> argsList = new ArrayList<Object[]>();
		for (T entity : list) {
			Object[] args = this.setArgs(entity, SQL_DELETE, id_name);
			argsList.add(args);
		}
		// int[] argTypes = this.setArgTypes(entity, SQL_DELETE);
		return jdbcTemplate.batchUpdate(sql, argsList);
	}

	/**
	 * 根据传入多个Id删除数据
	 */
	public void batchDeleteById(int[] ids, String id_name) {
		exists_id_name(id_name);
		StringBuilder sql = new StringBuilder(" DELETE FROM "
				+ entityClass.getSimpleName() + " WHERE " + id_name + " in ("); // )?)";
		for (Object id : ids) {
			sql.append("?,");
		}
		sql = sql.deleteCharAt(sql.length() - 1);
		sql.append(") ");

		int length = ids.length;
		Object[] args = new Object[length];
		for (int i = 0; i < length; i++) {
			args[i] = ids[i];
		}

		jdbcTemplate.update(sql.toString(), args);
	}

	/**
	 * 根据id查询
	 */
	public T findById(int id, String id_name) {
		exists_id_name(id_name);
		String sql = "SELECT * FROM " + entityClass.getSimpleName()
				+ "  WHERE " + id_name + "=? ";
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
		return jdbcTemplate.query(sql, rowMapper, id).get(0);
	}

	/**
	 * 全表查询
	 */
	public List<T> findAll() {
		String sql = "SELECT * FROM " + entityClass.getSimpleName();
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
		List<T> list = jdbcTemplate.query(sql, rowMapper);
		return jdbcTemplate.query(sql, rowMapper);
	}

	/**
	 * 查询条件加排序条件
	 * 
	 * @param pageNo
	 *            当前页
	 * @param pageSize
	 *            页面大小
	 * @return
	 */
	public QueryResult<T> findByPage(int pageNo, int pageSize) {
		List<T> list = this.find(pageNo, pageSize, null, null);
		int totalRow = this.count(null);
		return new QueryResult<T>(list, totalRow);
	}

	/**
	 * 查询条件加排序条件
	 * 
	 * @param pageNo
	 *            当前页
	 * @param pageSize
	 *            页面大小
	 * @param where
	 *            查询条件（第一个放属性名字，第二个放属性值）
	 * @return
	 */
	public QueryResult<T> findByPage(int pageNo, int pageSize,
			Map<String, String> where) {
		List<T> list = this.find(pageNo, pageSize, where, null);
		int totalRow = this.count(where);
		return new QueryResult<T>(list, totalRow);
	}

	/**
	 * 查询条件加排序条件
	 * 
	 * @param pageNo
	 *            当前页
	 * @param pageSize
	 *            页面大小
	 * @param orderby
	 *            排序条件（在key里面放排序字段就行，多个排序用多个）
	 * @return
	 */
	public QueryResult<T> findByPage(int pageNo, int pageSize,
			LinkedHashMap<String, String> orderby) {
		List<T> list = this.find(pageNo, pageSize, null, orderby);
		int totalRow = this.count(null);
		return new QueryResult<T>(list, totalRow);
	}

	/**
	 * 查询条件加排序条件
	 * 
	 * @param pageNo
	 *            当前页
	 * @param pageSize
	 *            页面大小
	 * @param where
	 *            查询条件（第一个放属性名字，第二个放属性值）
	 * @param orderby
	 *            排序条件（在key里面放排序字段就行，多个排序用多个）
	 * @return
	 */
	public QueryResult<T> findByPage(int pageNo, int pageSize,
			Map<String, String> where, LinkedHashMap<String, String> orderby) {
		List<T> list = this.find(pageNo, pageSize, where, orderby);
		int totalRow = this.count(where);
		return new QueryResult<T>(list, totalRow);
	}

	/**
	 * 
	 * @param pageNo
	 *            当前页
	 * @param pageSize
	 *            页面大小
	 * @param findsql
	 *            查询语句
	 * @param findargs
	 *            查询参数
	 * @return
	 */
	public QueryResult<T> findByPage(int pageNo, int pageSize, String findsql,
			ArrayList<Object> param) {
		List<T> list = this.normalFind(pageNo, pageSize, findsql, param);
		int totalRow = this.normalCount(findsql, param);
		return new QueryResult<T>(list, totalRow);
	}

	// 组装SQL
	private String makeSql(String sqlFlag, String id_name) {
		StringBuffer sql = new StringBuffer();
		StringBuffer argsnum = new StringBuffer();
		Field[] fields = entityClass.getDeclaredFields();
		if (sqlFlag.equals(SQL_INSERT)) {
			sql.append(" INSERT INTO " + entityClass.getSimpleName());
			sql.append("(");
			for (int i = 0; fields != null && i < fields.length; i++) {
				fields[i].setAccessible(true); // 暴力反射
				String column = fields[i].getName();
				if (column.equals(id_name) || "serialVersionUID".equals(column)) { // 传入主键名称
					continue;
				}
				sql.append(column).append(",");
				argsnum.append("?,");
			}
			sql = sql.deleteCharAt(sql.length() - 1);
			sql.append(") VALUES (");
			sql.append(argsnum);

			sql = sql.deleteCharAt(sql.length() - 1);
			sql.append(")");
		} else if (sqlFlag.equals(SQL_UPDATE)) {
			sql.append(" UPDATE " + entityClass.getSimpleName() + " SET ");
			for (int i = 0; fields != null && i < fields.length; i++) {
				fields[i].setAccessible(true); // 暴力反射
				String column = fields[i].getName();
				if ("serialVersionUID".equals(column)) { // 传入主键名称
					continue;
				}

				sql.append(column).append("=").append("?,");
			}
			sql = sql.deleteCharAt(sql.length() - 1);
			sql.append(" WHERE " + id_name + "=? ");
		} else if (sqlFlag.equals(SQL_DELETE)) {
			sql.append(" DELETE FROM " + entityClass.getSimpleName()
					+ " WHERE " + id_name + "=? ");
		}
		System.out.println("执行SQL=" + sql);
		return sql.toString();

	}

	// 设置参数
	private Object[] setArgs(T entity, String sqlFlag, String id_name) {

		Field[] fields = entityClass.getDeclaredFields();
		if (sqlFlag.equals(SQL_INSERT)) {
			ArrayList<Object> argLlist = new ArrayList<Object>();
			int length = fields.length;
			// Object[] args = new Object[fields.length];
			for (int i = 0; length > 0 && i < length; i++) {
				try {
					fields[i].setAccessible(true); // 暴力反射
					String column = fields[i].getName();
					if (column.equals(id_name)
							|| "serialVersionUID".equals(column)) { // 传入主键名称
						continue;
					}
					// args[i] = fields[i].get(entity);
					argLlist.add(fields[i].get(entity));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 打印参数
			StringBuffer argsString = new StringBuffer();
			argsString.append("参数：");
			for (Object oj : argLlist) {
				if (oj == null)
					argsString.append("null,");
				else
					argsString.append(oj.toString() + ",");
			}
			argsString = argsString.deleteCharAt(argsString.length() - 1);
			System.out.println(argsString);

			return argLlist.toArray();
		} else if (sqlFlag.equals(SQL_UPDATE)) {
			ArrayList<Object> argLlist = new ArrayList<Object>();
			int length = fields.length;
			// Object[] tempArr = new Object[fields.length];
			// for (int i = 0; length > 0 && i < length; i++) {
			// try {
			// fields[i].setAccessible(true); // 暴力反射
			// // tempArr[i] = fields[i].get(entity);
			// String column = fields[i].getName();
			// if (column.equals(id_name)
			// || "serialVersionUID".equals(column)) { // 传入主键名称
			// continue;
			// }
			// argLlist.add(fields[i].get(entity));
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			// }
			//
			Object temp_idvalue = new Object();
			for (int i = 0; length > 0 && i < length; i++) {
				try {
					fields[i].setAccessible(true); // 暴力反射
					String column = fields[i].getName();
					if ("serialVersionUID".equals(column))
						continue;

					if (column.equals(id_name))
						temp_idvalue = fields[i].get(entity);

					argLlist.add(fields[i].get(entity));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			argLlist.add(temp_idvalue);

			// 打印参数
			StringBuffer argsString = new StringBuffer();
			argsString.append("参数：");
			for (Object oj : argLlist) {
				if (oj == null)
					argsString.append("null,");
				else
					argsString.append(oj.toString() + ",");
			}
			argsString = argsString.deleteCharAt(argsString.length() - 1);

			System.out.println(argsString);

			return argLlist.toArray();
		} else if (sqlFlag.equals(SQL_DELETE)) {
			Object[] args = new Object[1]; // 长度是1
			int length = fields.length;
			for (int i = 0; length > 0 && i < length; i++) {
				try {
					fields[i].setAccessible(true); // 暴力反射
					String column = fields[i].getName();
					if (column.equals(id_name)) { // 传入主键名称
						args[0] = fields[i].get(entity);
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			StringBuffer argsString = new StringBuffer();
			argsString.append("参数：");
			for (Object oj : args) {
				if (oj == null)
					argsString.append("null,");
				else
					argsString.append(oj.toString() + ",");
			}
			argsString = argsString.deleteCharAt(argsString.length() - 1);
			System.out.println(argsString);
			return args;
		}
		return null;

	}

	// 设置参数类型（写的不全，只是一些常用的）
	private int[] setArgTypes(T entity, String sqlFlag) {
		Field[] fields = entityClass.getDeclaredFields();
		if (sqlFlag.equals(SQL_INSERT)) {
			int[] argTypes = new int[fields.length];
			try {
				for (int i = 0; argTypes != null && i < argTypes.length; i++) {
					fields[i].setAccessible(true); // 暴力反射
					if (fields[i].get(entity).getClass().getName()
							.equals("java.lang.String")) {
						argTypes[i] = Types.VARCHAR;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.lang.Double")) {
						argTypes[i] = Types.DECIMAL;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.lang.Integer")) {
						argTypes[i] = Types.INTEGER;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.util.Date")) {
						argTypes[i] = Types.DATE;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return argTypes;
		} else if (sqlFlag.equals(SQL_UPDATE)) {
			int[] tempArgTypes = new int[fields.length];
			int[] argTypes = new int[fields.length];
			try {
				for (int i = 0; tempArgTypes != null && i < tempArgTypes.length; i++) {
					fields[i].setAccessible(true); // 暴力反射
					if (fields[i].get(entity).getClass().getName()
							.equals("java.lang.String")) {
						tempArgTypes[i] = Types.VARCHAR;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.lang.Double")) {
						tempArgTypes[i] = Types.DECIMAL;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.lang.Integer")) {
						tempArgTypes[i] = Types.INTEGER;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.util.Date")) {
						tempArgTypes[i] = Types.DATE;
					}
				}
				System.arraycopy(tempArgTypes, 1, argTypes, 0,
						tempArgTypes.length - 1); // 数组拷贝
				argTypes[argTypes.length - 1] = tempArgTypes[0];

			} catch (Exception e) {
				e.printStackTrace();
			}
			return argTypes;

		} else if (sqlFlag.equals(SQL_DELETE)) {
			int[] argTypes = new int[1]; // 长度是1
			try {
				fields[0].setAccessible(true); // 暴力反射
				if (fields[0].get(entity).getClass().getName()
						.equals("java.lang.String")) {
					argTypes[0] = Types.VARCHAR;
				} else if (fields[0].get(entity).getClass().getName()
						.equals("java.lang.Integer")) {
					argTypes[0] = Types.INTEGER;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return argTypes;
		}
		return null;
	}

	private List<T> find(int pageNo, int pageSize, Map<String, String> where,
			LinkedHashMap<String, String> orderby) {
		// where 与 order by 要写在select * from table 的后面，而不是where rownum<=? )
		// where rn>=?的后面
		StringBuffer sql = new StringBuffer(" SELECT t.* FROM (SELECT * FROM "
				+ entityClass.getSimpleName());
		if (where != null && where.size() > 0) {
			sql.append(" WHERE "); // 注意不是where
			for (Map.Entry<String, String> me : where.entrySet()) {
				String columnName = me.getKey();
				String columnValue = me.getValue();
				sql.append(columnName).append("=").append(columnValue)
						.append(" AND "); // 没有考虑or的情况
			}
			int endIndex = sql.lastIndexOf("AND");
			if (endIndex > 0) {
				sql = new StringBuffer(sql.substring(0, endIndex));
			}
		}
		if (orderby != null && orderby.size() > 0) {
			sql.append(" ORDER BY ");
			for (Map.Entry<String, String> me : orderby.entrySet()) {
				String columnName = me.getKey();
				String columnValue = me.getValue();
				sql.append(columnName).append(" ").append(columnValue)
						.append(",");
			}
			sql = sql.deleteCharAt(sql.length() - 1);
		}
		sql.append(" ) t LIMIT ?,? ");
		System.out.println("SQL=" + sql);
		Object[] args = { (pageNo - 1) * pageSize, pageNo * pageSize };
		// 打印参数
		StringBuffer argsString = new StringBuffer();
		argsString.append("参数：");
		for (Object oj : args) {
			if (oj == null)
				argsString.append("null,");
			else
				argsString.append(oj.toString() + ",");
		}
		argsString = argsString.deleteCharAt(argsString.length() - 1);
		System.out.println(argsString);
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
		return jdbcTemplate.query(sql.toString(), args, rowMapper);
	}

	private List<T> normalFind(int pageNo, int pageSize, String findsql,
			ArrayList<Object> param) {
		if (pageSize == 0)
			pageSize = 100000;
		if (pageNo == 0)
			pageNo = 1;
		// where 与 order by 要写在select * from table 的后面，而不是where rownum<=? )
		// where rn>=?的后面
		StringBuffer sql = new StringBuffer(" SELECT t.* FROM (");
		sql.append(findsql);

		sql.append(" ) t LIMIT ?,? ");
		System.out.println("SQL=" + sql);
		// 组装参数
		int argLength = param.size();
		Object[] args = new Object[argLength + 2];
		for (int i = 0; argLength > 0 && i < argLength; i++) {
			args[i] = param.get(i);
		}
		args[argLength] = (pageNo - 1) * pageSize;
		args[argLength + 1] = pageNo * pageSize;
		// 打印参数
		StringBuffer argsString = new StringBuffer();
		argsString.append("参数：");
		for (Object oj : args) {
			if (oj == null)
				argsString.append("null,");
			else
				argsString.append(oj.toString() + ",");
		}
		argsString = argsString.deleteCharAt(argsString.length() - 1);
		System.out.println(argsString);
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
		return jdbcTemplate.query(sql.toString(), args, rowMapper);
	}

	private int normalCount(String findsql, ArrayList<Object> param) {

		StringBuffer sql = new StringBuffer(" SELECT COUNT(*) FROM ( ");
		sql.append(findsql);
		sql.append(" ) t ");
		System.out.println("normalcountSQL=" + sql);
		return jdbcTemplate.queryForInt(sql.toString(), param.toArray());

	}

	private int count(Map<String, String> where) {
		StringBuffer sql = new StringBuffer(" SELECT COUNT(*) FROM "
				+ entityClass.getSimpleName());
		if (where != null && where.size() > 0) {
			sql.append(" WHERE ");
			for (Map.Entry<String, String> me : where.entrySet()) {
				String columnName = me.getKey();
				String columnValue = me.getValue();
				sql.append(columnName).append("=").append(columnValue)
						.append(" AND "); // 没有考虑or的情况
			}
			int endIndex = sql.lastIndexOf("AND");
			if (endIndex > 0) {
				sql = new StringBuffer(sql.substring(0, endIndex));
			}
		}
		System.out.println("countSQL=" + sql);
		return jdbcTemplate.queryForInt(sql.toString());
	}

}

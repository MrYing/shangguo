package com.shangguo.dao.base;

import java.io.Serializable;
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

/**
 * 
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

	/** 设置一些操作的常量 */
	public static final String SQL_INSERT = "insert";
	public static final String SQL_UPDATE = "update";
	public static final String SQL_DELETE = "delete";
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
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

	public int save(T entity, String id_name) {
		exists_id_name(id_name);
		System.out.println("+++++++++++++");
		String sql = this.makeSql(SQL_INSERT, id_name);
		System.out.println(sql);
		Object[] args = this.setArgs(entity, SQL_INSERT, id_name);
		// int[] argTypes = this.setArgTypes(entity, SQL_INSERT);

		return jdbcTemplate.update(sql.toString(), args);
	}

	public int update(T entity, String id_name) {
		System.out.println("/////////////////");
		exists_id_name(id_name);
		String sql = this.makeSql(SQL_UPDATE, id_name);
		System.out.println(sql);
		Object[] args = this.setArgs(entity, SQL_UPDATE, id_name);
		// int[] argTypes = this.setArgTypes(entity, SQL_UPDATE);
		return jdbcTemplate.update(sql, args);
	}

	public int delete(T entity, String id_name) {
		System.out.println("-----------------");
		exists_id_name(id_name);
		String sql = this.makeSql(SQL_DELETE, id_name);
		System.out.println(sql);
		Object[] args = this.setArgs(entity, SQL_DELETE, id_name);
		// int[] argTypes = this.setArgTypes(entity, SQL_DELETE);
		return jdbcTemplate.update(sql, args);
	}

	public int delete(int id, String id_name) {
		exists_id_name(id_name);
		String sql = " DELETE FROM " + entityClass.getSimpleName() + " WHERE "
				+ id_name + "=?";
		return jdbcTemplate.update(sql, id);
	}

	/**
	 * 未完成
	 */

	public void batchSave(List<T> list) {

	}

	/**
	 * 未完成
	 */

	public void batchUpdate(List<T> list) {

	}

	/**
	 * 未完成
	 */

	public void batchDelete(List<T> list) {

	}

	public T findById(Serializable id, String id_name) {
		exists_id_name(id_name);
		String sql = "SELECT * FROM " + entityClass.getSimpleName()
				+ "  WHERE " + id_name + "=? ";
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
		return jdbcTemplate.query(sql, rowMapper, id).get(0);
	}

	public List<T> findAll() {
		String sql = "SELECT * FROM " + entityClass.getSimpleName();
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
		return jdbcTemplate.query(sql, rowMapper);
	}

	public QueryResult<T> findByPage(int pageNo, int pageSize) {
		List<T> list = this.find(pageNo, pageSize, null, null);
		int totalRow = this.count(null);
		return new QueryResult<T>(list, totalRow);
	}

	public QueryResult<T> findByPage(int pageNo, int pageSize,
			Map<String, String> where) {
		List<T> list = this.find(pageNo, pageSize, where, null);
		int totalRow = this.count(where);
		return new QueryResult<T>(list, totalRow);
	}

	public QueryResult<T> findByPage(int pageNo, int pageSize,
			LinkedHashMap<String, String> orderby) {
		List<T> list = this.find(pageNo, pageSize, null, orderby);
		int totalRow = this.count(null);
		return new QueryResult<T>(list, totalRow);
	}

	public QueryResult<T> findByPage(int pageNo, int pageSize,
			Map<String, String> where, LinkedHashMap<String, String> orderby) {
		List<T> list = this.find(pageNo, pageSize, where, orderby);
		int totalRow = this.count(where);
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
				if (column.equals(id_name) || "serialVersionUID".equals(column)) { // 传入主键名称
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
		System.out.println("SQL=" + sql);
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
			return argLlist.toArray();
		} else if (sqlFlag.equals(SQL_UPDATE)) {
			ArrayList<Object> argLlist = new ArrayList<Object>();
			int length = fields.length;
			// Object[] tempArr = new Object[fields.length];
			for (int i = 0; length > 0 && i < length; i++) {
				try {
					fields[i].setAccessible(true); // 暴力反射
					// tempArr[i] = fields[i].get(entity);
					String column = fields[i].getName();
					if (column.equals(id_name)
							|| "serialVersionUID".equals(column)) { // 传入主键名称
						continue;
					}
					argLlist.add(fields[i].get(entity));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// Object[] args = new Object[fields.length];
			// System.arraycopy(tempArr, 1, args, 0, tempArr.length - 1); //
			// 数组拷贝
			// args[args.length - 1] = tempArr[0];
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
		StringBuffer sql = new StringBuffer(
				" SELECT * FROM (SELECT t.*,ROWNUM rn FROM (SELECT * FROM "
						+ entityClass.getSimpleName());
		if (where != null && where.size() > 0) {
			sql.append(" WHERE "); // 注意不是where
			for (Map.Entry<String, String> me : where.entrySet()) {
				String columnName = me.getKey();
				String columnValue = me.getValue();
				sql.append(columnName).append(" ").append(columnValue)
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
		sql.append(" ) t WHERE ROWNUM<=? ) WHERE rn>=? ");
		System.out.println("SQL=" + sql);
		Object[] args = { pageNo * pageSize, (pageNo - 1) * pageSize + 1 };
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
		return jdbcTemplate.query(sql.toString(), args, rowMapper);
	}

	private int count(Map<String, String> where) {
		StringBuffer sql = new StringBuffer(" SELECT COUNT(*) FROM "
				+ entityClass.getSimpleName());
		if (where != null && where.size() > 0) {
			sql.append(" WHERE ");
			for (Map.Entry<String, String> me : where.entrySet()) {
				String columnName = me.getKey();
				String columnValue = me.getValue();
				sql.append(columnName).append(" ").append(columnValue)
						.append(" AND "); // 没有考虑or的情况
			}
			int endIndex = sql.lastIndexOf("AND");
			if (endIndex > 0) {
				sql = new StringBuffer(sql.substring(0, endIndex));
			}
		}
		System.out.println("SQL=" + sql);
		return jdbcTemplate.queryForInt(sql.toString());
	}

}

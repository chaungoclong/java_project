package dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import dao.IBaseDAO;
import database.Query;
import helper.Helper;
import mapper.Mapper;

public abstract class BaseDAO<T> implements IBaseDAO<T> {
	// database config
	protected static ResourceBundle config = ResourceBundle.getBundle("config.db");

	// tên bảng
	protected String table;

	// chuyển resultset thành bean
	protected Mapper<T> mapper;

	// các cột có thể insert
	protected String[] insertField;

	// cột khóa chính
	protected String primaryKey;

	public BaseDAO() {
		this.table = this.defaultTable();
		this.mapper = this.defaultMapper();
		this.insertField = new String[] {};
		this.primaryKey = "id";
	}

	public String getTable() {
		return table;
	}

	public Mapper<T> getMapper() {
		return mapper;
	}

	public String[] getInsertField() {
		return insertField;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	// tên bảng mặc định
	protected String defaultTable() {
		String className = this.getClass().getSimpleName().toLowerCase().toString();
		String search = "dao";
		String tableName = className + "s";

		int index = className.length() - search.length();
		String compareStr = className.substring(index);

		if (compareStr.equalsIgnoreCase(search)) {
			tableName = className.substring(0, index) + "s";
		}

		return tableName;
	}

	// mapper mặc định
	@SuppressWarnings({ "unchecked", "deprecation" })
	public Mapper<T> defaultMapper() {
		String className = this.getClass().getSimpleName().toLowerCase().toString();
		String search = "dao";
		String mapperClass = className + " Mapper";

		int index = className.length() - search.length();
		String compareStr = className.substring(index);

		if (compareStr.equalsIgnoreCase(search)) {
			mapperClass = className.substring(0, index) + " Mapper";
		}

		mapperClass = "mapper." + Helper.toCamel(mapperClass, true);
		Mapper<T> mapper = null;

		try {
			Class<?> cls = Class.forName(mapperClass);
			mapper = (Mapper<T>) cls.newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		return mapper;
	}

	@Override
	public int create(T object) {
		Map<String, Object> data = Helper.objectToHashMap(object);
		return this.create(data);
	}

	@Override
	public int create(Map<String, Object> data) {
		List<String> listField = Arrays.asList(this.insertField).stream().map(String::toLowerCase).collect(Collectors.toList());
		List<String> removeKey = new ArrayList<String>();
		
		// tìm tên cột không phù hợp
		for (String key : data.keySet()) {
			if (!listField.contains(key.toLowerCase())) {
				removeKey.add(key);
			}
		}
		
		// xóa cột không phù hợp
		for (String key : removeKey) {
			data.remove(key);
		}

		int insertedID = Query.table(this.table).insert(data);
		System.out.println(insertedID);

		return insertedID;
	}

	@Override
	public List<T> all() {
		return Query.table(this.table).get(this.mapper);
	}

	@Override
	public T find(Object id) {
		List<T> results = Query.table(this.table).select().where(this.primaryKey, "=", id).get(this.mapper);
		
		if (results != null && results.size() > 0) {
			return results.get(0);
		}
		
		return null;
	}

	@Override
	public int delete(Object id) {
		return Query.table(this.table).delete().where(this.primaryKey, "=", id).run();
	}

	@Override
	public int update(Object id, Map<String, Object> data) {
		return Query.table(this.table).update(data).where(this.primaryKey, "=", id).run();
	}

	@Override
	public int update(T object) {
		Map<String, Object> data = Helper.objectToHashMap(object);
		Object idValue = null;
		String[] idColumns = { this.primaryKey, Helper.toCamel(this.primaryKey, false) };

		for (String idColumn : idColumns) {
			if (data.containsKey(idColumn)) {
				idValue = data.get(idColumn);
				data.remove(idColumn);
				break;
			}
		}

		if (idValue != null) {
			return this.update(idValue, data);
		}

		return 0;
	}

}

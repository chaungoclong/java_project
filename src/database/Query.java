package database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import helper.Helper;
import mapper.Mapper;

public class Query {
	private static Query instance = new Query();
	private String table;
	private List<Object> bindParam;
	private StringBuilder sqlBuilder;
	private Database db;

	private Query() {
		this.db = Database.getInstance();
	}

	public static Query getInstance() {
		return instance;
	}

	public void reset() {
		this.table = null;
		this.bindParam = new ArrayList<Object>();
		this.sqlBuilder = null;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public static Query table(String table) {
		if (table == null) {
			throw new IllegalArgumentException();
		}

		Query query = Query.getInstance();
		query.reset();
		query.setTable(table);

		return query;
	}

	// select
	public Query select(String... columns) {
		if (columns == null) {
			throw new IllegalArgumentException();
		}

		this.sqlBuilder = new StringBuilder();

		this.sqlBuilder.append("SELECT ");

		if (columns.length == 0) {
			this.sqlBuilder.append("*");
		} else {
			this.sqlBuilder.append(String.join(",", columns));
		}

		this.sqlBuilder.append(" FROM ");
		this.sqlBuilder.append(this.table);

		return this;
	}

	public Query selectRaw(String sql, Object... params) {
		if (sql == null || params == null) {
			throw new IllegalArgumentException();
		}

		this.sqlBuilder = new StringBuilder();

		this.sqlBuilder.append("SELECT ");
		this.sqlBuilder.append(sql);
		this.sqlBuilder.append(" FROM ");
		this.sqlBuilder.append(this.table);

		this.bindParam.addAll(Arrays.asList(params));

		return this;
	}

	// where
	public Query addWhere(String column, String operator, Object value, String type) {
		if (column == null || operator == null || value == null) {
			throw new IllegalArgumentException();
		}

		if (this.sqlBuilder.lastIndexOf("WHERE") < 0) {
			type = "WHERE";
		} else {
			type = (type == null || type.isEmpty()) ? "AND" : type;
		}

		this.sqlBuilder.append(" " + type + " " + column + " " + operator + " ?");
		this.bindParam.add(value);

		return this;
	}

	public Query where(String column, String operator, Object value) {
		return this.addWhere(column, operator, value, "AND");
	}

	public Query orWhere(String column, String operator, Object value) {
		return this.addWhere(column, operator, value, "OR");
	}

	public Query addWhereRaw(String type, String sql, Object... params) {
		if (sql == null || params == null) {
			throw new IllegalArgumentException();
		}

		if (this.sqlBuilder.lastIndexOf("WHERE") < 0) {
			type = "WHERE";
		} else {
			type = (type == null || type.isEmpty()) ? "AND" : type;
		}

		this.sqlBuilder.append(" " + type + " " + sql);
		this.bindParam.addAll(Arrays.asList(params));

		return this;
	}

	public Query whereRaw(String sql, Object... params) {
		return this.addWhereRaw("AND", sql, params);
	}

	public Query orWhereRaw(String sql, Object... params) {
		return this.addWhereRaw("OR", sql, params);
	}

	// limit
	public Query limit(int limit, int offset) {
		this.sqlBuilder.append(" LIMIT " + offset + ", " + limit);

		return this;
	}

	public Query limit(int limit) {
		return this.limit(limit, 0);
	}

	// group by
	public Query groupBy(String... columns) {
		if (columns == null) {
			throw new IllegalArgumentException();
		}

		this.sqlBuilder.append(" GROUP BY ");
		this.sqlBuilder.append(String.join(", ", columns));

		return this;
	}

	// having
	public Query having(String column, String operator, Object... values) {
		if (column == null || operator == null || values == null) {
			throw new IllegalArgumentException();
		}

		this.sqlBuilder.append(" HAVING ");

		switch (operator) {
		case "BETWEEN":
			System.out.println(values.length);
			if (values.length != 2) {
				throw new IllegalArgumentException();
			}

			this.sqlBuilder.append(column + " " + operator + " ? AND ?");
			this.bindParam.addAll(Arrays.asList(values));
			break;

		default:
			if (values.length == 0) {
				throw new IllegalArgumentException();
			}

			this.sqlBuilder.append(column + " " + operator + " ?");
			this.bindParam.add(values[0]);
			break;
		}

		return this;
	}

	public Query havingBetween(String column, Object... values) {
		return this.having(column, "BETWEEN", values);
	}

	public Query havingRaw(String sql, Object... params) {
		if (sql == null || params == null) {
			throw new IllegalArgumentException();
		}

		this.sqlBuilder.append(" HAVING " + sql);
		this.bindParam.addAll(Arrays.asList(params));

		return this;

	}

	// order by
	public Query orderBy(String type, String... columns) {
		if (columns == null) {
			throw new IllegalArgumentException();
		}

		type = type == null ? "ASC" : type;

		this.sqlBuilder.append(" ORDER BY ");
		this.sqlBuilder.append(String.join(", ", columns));
		this.sqlBuilder.append(" " + type);

		return this;
	}

	// insert
	public int insert(Map<String, Object> data) {
		if (data == null) {
			throw new IllegalArgumentException();
		}

		String sql = QueryBuilder.insertInto(this.table, data).render();
		System.out.println(sql);
		Object[] params = data.values().toArray();

		return this.db.__insert(sql, params);
	}

	public long insert(Object object, String... cannotInsert) {
		if (object == null) {
			throw new IllegalArgumentException();
		}

		Map<String, Object> data = Helper.objectToHashMapIgnoreField(object, cannotInsert);

		return this.insert(data);
	}

	// delete
	public Query delete() {
		this.sqlBuilder = new StringBuilder();

		this.sqlBuilder.append("DELETE FROM ");
		this.sqlBuilder.append(this.table);

		return this;
	}

	// update
	public Query update(Map<String, Object> data) {
		if (data == null) {
			throw new IllegalArgumentException();
		}

		this.sqlBuilder = new StringBuilder();

		String sql = QueryBuilder.updateSet(this.table, data).render();
		this.sqlBuilder.append(sql);

		Object[] params = data.values().toArray();
		this.bindParam.addAll(Arrays.asList(params));

		return this;
	}

	// lấy kết quả từ câu lệnh SELECT
	public List<Result> get() {
		String sql = this.sqlBuilder == null ? "SELECT * FROM " + this.table : this.sqlBuilder.toString();
		Object[] params = this.bindParam.toArray();

		System.out.println(sql);
		return this.db.__query(sql, params);
	}

	public <T> List<T> get(Mapper<T> mapper) {
		String sql = this.sqlBuilder == null ? "SELECT * FROM " + this.table : this.sqlBuilder.toString();
		Object[] params = this.bindParam.toArray();

		List<T> results = new ArrayList<T>();
		List<Result> wrapperResult = this.db.__query(sql, params);

		for (Result result : wrapperResult) {
			results.add(mapper.toBean(result));
		}

		return results;
	}

	// thực thi câu lênh: UPDATE, DELETE
	public int run() {
		String sql = this.sqlBuilder.toString();
		Object[] params = this.bindParam.toArray();

		return this.db.__exec(sql, params);
	}

	// get sql
	public void getSQL() {
		System.out.println(this.sqlBuilder.toString());
		System.out.println(this.bindParam.toString());
	}

	public static void main(String[] args) {
//		List<Result> list = Query.table("tests").select("name").groupBy("name").orderBy("DESC", "name").limit(1).get();
//
//		for (Result result : list) {
//			System.out.println(result.get("id") + "|" + result.get("name"));
//		}
//		Query.table("a").selectRaw("count(*) as count, id").where("name", "LIKE", "%a%").groupBy("id")
//				.havingRaw("id between ? and ?", 10, 1000).getSQL();

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("name", "TEST_UPDATE_3");
//		Test t = new Test();
//		t.setId((long) 1);
//		t.setName("TEST_INSERT6");
//		
//		System.out.println(Query.table("tests").insert(t, "id"));

		Query.table("tests").update(data).where("id", ">", 5).run();
	}

}

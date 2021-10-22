package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ->simple and easy to use
public class QueryBuilder {
	private StringBuilder sqlBuilder;

	public QueryBuilder(StringBuilder sqlBuilder) {
		super();
		this.sqlBuilder = sqlBuilder;
	}

	// SELECT
	public static QueryBuilder select(String... fields) {
		StringBuilder sqlBuilder = new StringBuilder();

		if (fields == null) {
			throw new IllegalArgumentException();
		}

		sqlBuilder.append("SELECT ");
		if (fields.length == 0) {
			sqlBuilder.append("*");
		} else {
			sqlBuilder.append(String.join(", ", fields));
		}

		return new QueryBuilder(sqlBuilder);
	}

	// INSERT
	public static QueryBuilder insert(String table, String... fields) {
		if (table == null || fields == null) {
			throw new IllegalArgumentException();
		}

		StringBuilder sqlBuilder = new StringBuilder();

		sqlBuilder.append("INSERT INTO ");
		sqlBuilder.append(table);
		sqlBuilder.append("(");
		sqlBuilder.append(String.join(", ", fields));
		sqlBuilder.append(")");

		return new QueryBuilder(sqlBuilder);
	}

	// VALUES
	public QueryBuilder values(Object... params) {
		if (params == null || params.length == 0) {
			throw new IllegalArgumentException();
		}

		this.sqlBuilder.append(" VALUES(");
		this.sqlBuilder.append("?");
		this.sqlBuilder.append(", ?".repeat(params.length - 1));
		this.sqlBuilder.append(")");

		return this;
	}

	// INSERT INTO
	public static QueryBuilder insertInto(String table, Map<String, Object> values) {
		if (table == null || values.size() == 0) {
			throw new IllegalArgumentException();
		}

		StringBuilder sqlBuilder = new StringBuilder();
		List<String> fields = new ArrayList<String>(values.keySet());

		sqlBuilder.append("INSERT INTO ");
		sqlBuilder.append(table);
		sqlBuilder.append("(");

		int size = fields.size();

		for (int i = 0; i < size; ++i) {
			if (i > 0) {
				sqlBuilder.append(", ");
			}
			sqlBuilder.append(fields.get(i));
		}

		sqlBuilder.append(")");
		sqlBuilder.append(" VALUES(");
		sqlBuilder.append("?");
		sqlBuilder.append(", ?".repeat(size - 1));
		sqlBuilder.append(")");

		return new QueryBuilder(sqlBuilder);
	}

	// UPDATE
	public static QueryBuilder update(String table) {
		if (table == null) {
			throw new IllegalArgumentException();
		}

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("UPDATE ");
		sqlBuilder.append(table);

		return new QueryBuilder(sqlBuilder);
	}
	
	public static QueryBuilder updateSet(String table, Map<String, Object> setData) {
		if (setData == null) {
			throw new IllegalArgumentException();
		}

		StringBuilder sqlBuilder = new StringBuilder();
		
		sqlBuilder.append("UPDATE ");
		sqlBuilder.append(table);
		
		sqlBuilder.append(" SET ");

		List<String> fields = new ArrayList<>(setData.keySet());
		for (int i = 0; i < fields.size(); ++i) {
			if (i > 0) {
				sqlBuilder.append(", ");
			}
			sqlBuilder.append(fields.get(i));
			sqlBuilder.append(" = ");
			sqlBuilder.append("?");
		}

		return new QueryBuilder(sqlBuilder);
	}

	// SET
	public QueryBuilder set(String... fields) {
		if (fields == null) {
			throw new IllegalArgumentException();
		}

		this.sqlBuilder.append(" SET ");

		for (int i = 0; i < fields.length; ++i) {
			if (i > 0) {
				this.sqlBuilder.append(", ");
			}
			this.sqlBuilder.append(fields[i]);
			this.sqlBuilder.append(" = ");
			this.sqlBuilder.append("?");
		}
		return this;
	}

	public QueryBuilder set(Map<String, Object> setData) {
		if (setData == null) {
			throw new IllegalArgumentException();
		}

		this.sqlBuilder.append(" SET ");

		List<String> fields = new ArrayList<>(setData.keySet());
		for (int i = 0; i < fields.size(); ++i) {
			if (i > 0) {
				this.sqlBuilder.append(", ");
			}
			this.sqlBuilder.append(fields.get(i));
			this.sqlBuilder.append(" = ");
			this.sqlBuilder.append("?");
		}

		return this;
	}

	// DELETE
	public static QueryBuilder deleteFrom(String table) {
		if (table == null) {
			throw new IllegalArgumentException();
		}

		StringBuilder sqlBuilder = new StringBuilder();

		sqlBuilder.append("DELETE FROM ");
		sqlBuilder.append(table);

		return new QueryBuilder(sqlBuilder);
	}

	// FROM
	public QueryBuilder from(String table) {
		if (table == null) {
			throw new IllegalArgumentException();
		}

		this.sqlBuilder.append(" FROM ");
		this.sqlBuilder.append(table);

		return this;
	}

	// WHERE
	public QueryBuilder where(String condition) {
		if (condition == null) {
			throw new IllegalArgumentException();
		}

		this.sqlBuilder.append(" WHERE ");
		this.sqlBuilder.append(condition);

		return this;
	}

	public QueryBuilder whereIn(String field, Object... params) {
		if (field == null || params == null || params.length == 0) {
			throw new IllegalArgumentException();
		}

		if (this.sqlBuilder.lastIndexOf("WHERE") < 0) {
			this.sqlBuilder.append(" WHERE ");
		} else {
			this.sqlBuilder.append(" AND ");
		}

		this.sqlBuilder.append(field);
		this.sqlBuilder.append(" IN(");
		this.sqlBuilder.append("?");
		this.sqlBuilder.append(", ?".repeat(params.length - 1));
		this.sqlBuilder.append(")");

		return this;
	}

	public QueryBuilder whereNotIn(String field, Object... params) {
		if (field == null || params == null || params.length == 0) {
			throw new IllegalArgumentException();
		}

		if (this.sqlBuilder.lastIndexOf("WHERE") < 0) {
			this.sqlBuilder.append(" WHERE ");
		} else {
			this.sqlBuilder.append(" AND ");
		}

		this.sqlBuilder.append(field);
		this.sqlBuilder.append(" NOT IN(");
		this.sqlBuilder.append("?");
		this.sqlBuilder.append(", ?".repeat(params.length - 1));
		this.sqlBuilder.append(")");

		return this;
	}

	public QueryBuilder orWhereIn(String field, Object... params) {
		if (field == null || params == null || params.length == 0) {
			throw new IllegalArgumentException();
		}

		if (this.sqlBuilder.lastIndexOf("WHERE") < 0) {
			this.sqlBuilder.append(" WHERE ");
		} else {
			this.sqlBuilder.append(" OR ");
		}

		this.sqlBuilder.append(field);
		this.sqlBuilder.append(" IN(");
		this.sqlBuilder.append("?");
		this.sqlBuilder.append(", ?".repeat(params.length - 1));
		this.sqlBuilder.append(")");

		return this;
	}

	public QueryBuilder orWhereNotIn(String field, Object... params) {
		if (field == null || params == null || params.length == 0) {
			throw new IllegalArgumentException();
		}

		if (this.sqlBuilder.lastIndexOf("WHERE") < 0) {
			this.sqlBuilder.append(" WHERE ");
		} else {
			this.sqlBuilder.append(" OR ");
		}

		this.sqlBuilder.append(field);
		this.sqlBuilder.append(" NOT IN(");
		this.sqlBuilder.append("?");
		this.sqlBuilder.append(", ?".repeat(params.length - 1));
		this.sqlBuilder.append(")");

		return this;
	}

	// ORDER BY
	public QueryBuilder orderBy(String field, String type) {
		if (field == null) {
			throw new IllegalArgumentException();
		}

		type = type == null ? "ASC" : type;

		this.sqlBuilder.append(" ORDER BY ");
		this.sqlBuilder.append(field);
		this.sqlBuilder.append(" ");
		this.sqlBuilder.append(type);

		return this;
	}

	// JOIN
	public QueryBuilder join(String table, String condition) {
		if (table == null || condition == null) {
			throw new IllegalArgumentException();
		}

		this.sqlBuilder.append(" JOIN ");
		this.sqlBuilder.append(table);
		this.sqlBuilder.append(" ON ");
		this.sqlBuilder.append(condition);

		return this;
	}

	public QueryBuilder rightJoin(String table, String condition) {
		if (table == null || condition == null) {
			throw new IllegalArgumentException();
		}

		this.sqlBuilder.append(" RIGTH JOIN ");
		this.sqlBuilder.append(table);
		this.sqlBuilder.append(" ON ");
		this.sqlBuilder.append(condition);

		return this;
	}

	public QueryBuilder leftJoin(String table, String condition) {
		if (table == null || condition == null) {
			throw new IllegalArgumentException();
		}

		this.sqlBuilder.append(" LEFT JOIN ");
		this.sqlBuilder.append(table);
		this.sqlBuilder.append(" ON ");
		this.sqlBuilder.append(condition);

		return this;
	}

	// RENDER
	public String render() {
		String sql = this.sqlBuilder.toString();
		// System.out.println(sql);
		return sql;
	}

	public static void main(String[] args) {
		QueryBuilder.select("a", "b", "c").from("table").where("a > ? AND b > ?").orWhereIn("a", 1, 2, 3).render();
		QueryBuilder.insert("test", "a", "b", "c").values("x", 1, "ok").render();

		Map<String, Object> m = new HashMap<String, Object>();
		m.put("test1", 123);
		m.put("test2", 456);
		m.put("test3", 789);
		m.put("test4", 8910);
		
		for (String k : m.keySet()) {
			System.out.println(k);
		}
		
		for (Object o : m.values().toArray()) {
			System.out.println(o);
		}
		QueryBuilder.updateSet("table", m).render();

		QueryBuilder.update("abc").set("a", "b", "c").where("id > ?").render();
		QueryBuilder.update("abc").set(m).where("id > ?").render();

		QueryBuilder.deleteFrom("abc").where("a > ?").render();
		QueryBuilder.select("a", "b", "c").from("table").join("xyz", "table.id = xyz.id")
				.leftJoin("ok", "xyz.id = ok.id").rightJoin("yes", "ok.id = yes.id").where("a > ? AND b > ?")
				.orderBy("a", "desc").render();

	}
}

package database;

import java.util.List;

public interface IDatabase {
	// câu lênh SELECT
	public List<Result> __query(String sql, Object... params);

	public <T> List<T> __query(Class<T> clazz, String sql, Object... params);

	// câu lệnh DELETE, UPDATE
	public int __exec(String sql, Object... params);

	// câu lênh INSERT(return id của hàng vừa thêm)
	public int __insert(String sql, Object... params);
	
	// câu lệnh COUNT
	public int __count(String table, String column, String condition, Object ...params);
	
	public int __count(String table, String column, Object ...params);

}

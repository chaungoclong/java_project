package database;

import java.util.List;

public interface IDatabase {
	// câu lênh SELECT
	public List<Result> __query(String sql, Object... params);

	public int __exec(String sql, Object... params);

	// câu lênh INSERT(return id của hàng vừa thêm)
	public int __insert(String sql, Object... params);

}

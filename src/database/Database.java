package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import mapper.AutoMapper;

public class Database implements IDatabase {
	// database config
	protected static ResourceBundle config = ResourceBundle.getBundle("config.db");
	private static Database instance = new Database();

	private Database() {

	}

	public static Database getInstance() {
		return instance;
	}

	// kết nối
	public Connection getConnection() {
		try {
			String driver = config.getString("driver");
			String url = config.getString("url");
			String userName = config.getString("username");
			String password = config.getString("password");

			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, userName, password);
			return connection;
		} catch (Exception e) {
			return null;
		}
	}

	// bind param vào SQL
	protected void bindParam(PreparedStatement pstmt, Object... params) {
		try {
			int index = 1;
			for (Object param : params) {
				pstmt.setObject(index, param);
				index++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Result> __query(String sql, Object... params) {
		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql);) {
			List<Result> results = new ArrayList<Result>();

			bindParam(statement, params);

			try (ResultSet resultSet = statement.executeQuery();) {
				results = Result.asList(resultSet);
			}

			return results;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public <T> List<T> __query(Class<T> clazz, String sql, Object... params) {
		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql);) {
			List<T> results = new ArrayList<T>();

			bindParam(statement, params);

			try (ResultSet resultSet = statement.executeQuery();) {
				results = AutoMapper.mapList(clazz, resultSet);
			}

			return results;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int __insert(String sql, Object... params) {
		int insertedID = -1;

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			bindParam(statement, params);

			if (statement.executeUpdate() > 0) {
				try (ResultSet resultSet = statement.getGeneratedKeys()) {
					if (resultSet.next()) {
						insertedID = resultSet.getInt(1);
					}
				}
			}
		} catch (SQLException e) {
			return -1;
		}

		return insertedID;
	}

	@Override
	public int __exec(String sql, Object... params) {
		int affectedRow = 0;

		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
			bindParam(statement, params);
			affectedRow = statement.executeUpdate();
		} catch (SQLException e) {
			return 0;
		}
		return affectedRow;
	}

	public static void main(String[] args) {
		Database db = new Database();
		List<Result> list = db.__query("select * from tests");
		System.out.println(list.get(0).get("id"));
	}
}

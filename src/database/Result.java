package database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {
	private Map<String, Object> dataInRow;

	public Result(Map<String, Object> dataInRow) {
		super();
		this.dataInRow = dataInRow;
	}

	public Map<String, Object> getDataInRow() {
		return dataInRow;
	}

	public Object get(String column) {
		return this.dataInRow.get(column);
	}

	public void setDataInRow(Map<String, Object> dataInRow) {
		this.dataInRow = dataInRow;
	}

	public static List<Result> asList(ResultSet resultSet) {
		List<Result> rows = new ArrayList<Result>();
		try {
			ResultSetMetaData metaData = resultSet.getMetaData();
			int numberColumn = metaData.getColumnCount();

			while (resultSet.next()) {
				Map<String, Object> row = new HashMap<String, Object>(numberColumn);

				for (int i = 1; i <= numberColumn; i++) {
					row.put(metaData.getColumnName(i), resultSet.getObject(i));
				}

				rows.add(new Result(row));
			}

		} catch (Exception e) {
			return null;
		}

		return rows;
	}
}

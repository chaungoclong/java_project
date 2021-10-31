package helper;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Helper {
	// lấy tên các thuộc tính của object
	public static List<Field> getObjectField(Class<?> type, boolean getSuperClassField) {
		List<Field> fields = new ArrayList<Field>();

		if (getSuperClassField) {
			for (Class<?> cls = type; cls != null; cls = cls.getSuperclass()) {
				fields.addAll(Arrays.asList(cls.getDeclaredFields()));
			}
		} else {
			fields.addAll(Arrays.asList(type.getDeclaredFields()));
		}

		return fields;
	}

	public static List<Field> getObjectField(Object object, boolean getSuperClassField) {
		Class<?> type = object.getClass();
		List<Field> fields = new ArrayList<Field>();

		if (getSuperClassField) {
			for (Class<?> cls = type; cls != null; cls = cls.getSuperclass()) {
				fields.addAll(Arrays.asList(cls.getDeclaredFields()));
			}
		} else {
			fields.addAll(Arrays.asList(type.getDeclaredFields()));
		}

		return fields;
	}

	// chuyển Object thành HashMap
	public static Map<String, Object> objectToHashMap(Object object) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Field> fields = getObjectField(object, true);

		try {
			for (Field field : fields) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(object));
			}
		} catch (Exception e) {
			return null;
		}

		return map;
	}

	public static Map<String, Object> objectToHashMapIgnoreField(Object object, String... ignore) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Field> fields = getObjectField(object, true);
		List<String> ignoreField = Arrays.asList(ignore);

		try {
			for (Field field : fields) {
				if (ignoreField.contains(field.getName())) {
					continue;
				}

				field.setAccessible(true);
				map.put(field.getName(), field.get(object));
			}
		} catch (Exception e) {
			return null;
		}

		return map;
	}

	// format chuỗi về kiểu Camel
	public static String toCamel(String string, boolean type) {
		if (string == null) {
			return null;
		}

		String[] stringArray = string.split("[\\_\s]+");

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < stringArray.length; i++) {
			String word = stringArray[i];

			if (i == 0 && type == false) {
				word = word.isEmpty() ? word : word.toLowerCase();
			} else {
				word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
			}

			builder.append(word);
		}

		return builder.toString();
	}

	// nối nhiều mảng
	public static Object[] arrConcat(Object[]... values) {
		System.out.println(values);
		return Stream.of(values).flatMap(Stream::of).toArray(Object[]::new);
	}

	// chuyển resultSet thành List HashMap
	public static List<Map<String, Object>> resultSetToListHashMap(ResultSet resultSet) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();

		try {
			ResultSetMetaData metaData = resultSet.getMetaData();
			int numberColumns = metaData.getColumnCount();

			while (resultSet.next()) {
				Map<String, Object> row = new HashMap<String, Object>(numberColumns);

				for (int i = 1; i <= numberColumns; i++) {
					row.put(metaData.getColumnName(i), resultSet.getObject(i));
				}

				rows.add(row);
			}

			resultSet.close();
		} catch (Exception e) {
			return null;
		}

		return rows;
	}

	public static void main(String[] args) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_project", "long", "tnt");

			PreparedStatement pstmt = conn.prepareStatement("select * from users");

			ResultSet rs = pstmt.executeQuery();

			List<Map<String, Object>> result = Helper.resultSetToListHashMap(rs);

			for (Map<String, Object> map : result) {
				for (String k : map.keySet()) {
					System.out.println(k + ":" + map.get(k));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

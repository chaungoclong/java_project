package mapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import helper.Helper;

public class AutoMapper {
	// map một danh sách object
	public static <T> List<T> mapList(Class<T> clazz, ResultSet resultSet) {
		List<Map<String, Object>> datas = Helper.resultSetToListHashMap(resultSet);

		System.out.println(datas.size());
		List<T> results = new ArrayList<T>(datas.size());

		for (Map<String, Object> map : datas) {
			results.add(map(clazz, map));
		}

		return results;
	}

	// map một object
	@SuppressWarnings("deprecation")
	public static <T> T map(Class<T> clazz, Map<String, Object> data) {
		T object = null;

		try {
			object = clazz.newInstance();

			Map<String, Object> newData = new HashMap<String, Object>(data.size());

			// đồng bộ key về chữ thường và loại bỏ dấu gạch dưới: column1 = COLUMN1 = Column_1 = colum_n1
			for (String key : data.keySet()) {
				String newKey = Helper.toCamel(key, false).toLowerCase();

				newData.put(newKey, data.get(key));
			}

			System.out.println(newData);

			List<Field> fields = Helper.getObjectField(object, true);

			for (Field field : fields) {
				String fieldName = field.getName().toLowerCase();

				if (newData.containsKey(fieldName)) {
					setField(object, field, newData.get(fieldName));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return object;
	}

	public static boolean setField(Object object, Field field, Object value) {
		boolean status = false;

		try {
			field.setAccessible(true);

			String type = field.getType().getSimpleName();

			// string
			if (type.equalsIgnoreCase("String")) {
				System.out.println(value);
				field.set(object, String.valueOf(value));

				status = true;
			}

			// short
			if (type.equalsIgnoreCase("short") || type.equalsIgnoreCase("Short")) {
				System.out.println(value);
				field.set(object, Short.valueOf(String.valueOf(value)));

				status = true;
			}

			// int
			if (type.equalsIgnoreCase("int") || type.equalsIgnoreCase("Integer")) {
				System.out.println(value);
				field.set(object, Integer.valueOf(String.valueOf(value)));

				status = true;
			}

			// long
			if (type.equalsIgnoreCase("long") || type.equalsIgnoreCase("Long")) {
				System.out.println(value);
				field.set(object, Long.valueOf(String.valueOf(value)));

				status = true;
			}

			// float
			if (type.equalsIgnoreCase("float") || type.equalsIgnoreCase("Float")) {
				System.out.println(value);
				field.set(object, Float.valueOf(String.valueOf(value)));

				status = true;
			}

			// double
			if (type.equalsIgnoreCase("double") || type.equalsIgnoreCase("Double")) {
				System.out.println(value);
				field.set(object, Double.valueOf(String.valueOf(value)));

				status = true;
			}

			// boolean
			if (type.equalsIgnoreCase("boolean") || type.equalsIgnoreCase("Boolean")) {
				System.out.println(value);
				field.set(object, Boolean.parseBoolean(String.valueOf(value)));

				status = true;
			}

			// char
			if (type.equalsIgnoreCase("char") || type.equalsIgnoreCase("Character" + "")) {
				System.out.println(value);
				field.set(object, String.valueOf(value).charAt(0));

				status = true;
			}

		} catch (Exception e) {
			status = false;
		}

		return status;
	}

	public static void main(String[] args) {

	}
}

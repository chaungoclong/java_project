package helper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import bean.Test;

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

	public static Map<String, Object> objectToHashMapIgnoreField(Object object, String ...ignore) {
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

	public static void main(String[] args) {
		System.out.println(Helper.toCamel("ten lua", true));
		
	}
}

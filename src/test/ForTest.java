package test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import bean.Test;
import bean.TestMapper;
import bean.User;
import dao.impl.TestDAO;
import helper.Helper;
import mapper.AutoMapper;

public class ForTest {
	public static void show(Object... p) {
		System.out.println(p);
		for (Object object : p) {
			System.out.println(object);
		}
	}

	public void show() {
		System.out.println("test");

	}

	public static List<Field> getAllFields(Class<?> type) {
		List<Field> fields = new ArrayList<Field>();
		for (Class<?> c = type; c != null; c = c.getSuperclass()) {
			fields.addAll(Arrays.asList(c.getDeclaredFields()));
		}
		return fields;
	}

	public static void main(String[] args)
			throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		Test t = new Test();
		Class<?> c = Test.class;
		Class<?> e = Class.forName("bean.Test");
		Class<?> d = t.getClass();
		System.out.println(c + "|" + e + "|" + d);
////		
//		TestDAO dao = new TestDAO();
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("name", "TEST3");
//		dao.create(params);
//		List<Test> list = dao.all();
//
//		for (Test test : list) {
//			System.out.println("@" + test.getId() + "-" + test.getName());
//		}
//		String[] x = {"a", "b", "c"};
//		show("a", "b", "c");
//		show(x);
//		Test t = new Test();
//		t.setName("abc");
//		t.setId((long) 1);
//		
//		Map<String, Object> map = Helper.objectToHashMap(t);
//		
//		System.out.println(map);
//		Pattern p = Pattern.compile(".*(DAO)");
//		String input = "TestDAODAO";
//		Matcher m = p.matcher(input);
//		if (m.find()) {
//		    // replace first number with "number" and second number with the first
//		    String output = m.replaceFirst("number $1");  // number 46
//		    System.out.println(output);
//		}
//		
//		TestDAO dao = new TestDAO();
//		System.out.println(dao.getTable());
//		System.out.println(dao.defaultMapper());
//		System.out.println(dao.find(1).getName());

//		String str = "abcxyz";
//		String need = "xyo";
//		String result = str;
//		
//		String cmpStr = str.substring(str.length() - need.length());
//		if (cmpStr.equalsIgnoreCase(need)) {
//			result = str.substring(0, str.length() - need.length());
//		}
//		
//		System.out.println(cmpStr + ":" + result);
//		ForTest test = new ForTest();
//		ForTest.show(1, 2, 3);
//		test.show();

//		String[] a = {"a", "b", "c"};
//		Integer[] b = {1, 2, 3};
//		Integer[] c = {4, 5, 6};
//		
//		Object[] all = Helper.arrConcat(a, b, c);
//		
//		for (Object object : all) {
//			System.out.println(object);
//		}

//		ForTest.show(a);
////		
//		Test t = new Test();
//		t.setId(1);
//		t.setName("abc-xyz-okok");
//		
//		Map<String, Object> data = Helper.objectToHashMapIgnoreField(t, "name");
//		for (String k : data.keySet()) {
//			System.out.println(k + "-" + data.get(k));
//		}

//		TestDAO dao = new TestDAO();
//		dao.create(t);
//		List<Test> ls = dao.all();
//
//		for (Test test : ls) {
//			System.out.println(test.getId() + "|" + test.getName());
//		}

//		Map<String, Object> data = new HashMap<String, Object>();
//		data.put("varshort", null);
//		data.put("varint", 1000);
//		data.put("varlong", 10000);
//		data.put("varfloat", 10000.0f);
//		data.put("vardouble", "ok");
//		data.put("varboolean", "0");
//		data.put("varchar", "a");
//		data.put("vars_tring", "StringOKGood");
//		data.put("test", null);
//
//		TestMapper test = AutoMapper.map(TestMapper.class, data);
//
//		System.out.println(test);
//		
//		try {
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_project", "long", "tnt");
//
//			PreparedStatement pstmt = conn.prepareStatement("select * from users");
//
//			ResultSet rs = pstmt.executeQuery();
//
//			List<User> users = AutoMapper.mapList(User.class, rs);
//			
//			for (User user : users) {
//				System.out.println(user);
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
		
//		List<Test> ls = new TestDAO().all();
//		
//		for (Test test : ls) {
//			System.out.println(test.getId() + "-" + test.getName());
//		}
		
		List<String> a = new ArrayList<String>();
		String[] b = {"okla"};
		
		a.addAll(Arrays.asList(b));
		
		System.out.println(a.toString());
	}
}

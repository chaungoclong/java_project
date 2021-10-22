package dao.impl;

import dao.ITestDAO;
import database.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Test;

public class TestDAO extends BaseDAO<Test> implements ITestDAO {

	public TestDAO() {
		this.insertField = new String[] { "name" };
	}

	public static void main(String[] args) {
		TestDAO dao = new TestDAO();
		Test t = new Test();
		t.setName("TEST_new_123_new_xyz");
		t.setId(10);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("name", "new TEST_UPDATE_NEW");
//		System.out.println(dao.create(t));
//		Query.table("tests").update(data).where("id", "=", 9).run();
//		Test find = dao.find(9);
//
//		System.out.println(find.getId() + "-" + find.getName());
		System.out.println(dao.update(t));
	}
}

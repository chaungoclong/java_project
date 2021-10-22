package mapper;

import bean.Test;
import database.Result;

public class TestMapper implements Mapper<Test> {

	@Override
	public Test toBean(Result result) {
		Test test = new Test();

		test.setId((int) result.get("id"));
		test.setName((String) result.get("name"));

		return test;
	}
}

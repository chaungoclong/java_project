package mapper;

import bean.User;
import database.Result;

public class UserMapper implements Mapper<User> {

	@Override
	public User toBean(Result result) {
		User user = new User();

		user.setId((int) result.get("id"));
		user.setEmail((String) result.get("email"));
		user.setUserName((String) result.get("username"));
		user.setPassword((String) result.get("password"));

		return user;
	}

}

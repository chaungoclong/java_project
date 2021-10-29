package dao.impl;

import java.util.List;

import bean.User;
import dao.IUserDAO;
import database.Query;

public class UserDAO extends BaseDAO<User> implements IUserDAO {
	public UserDAO() {
		this.insertField = new String[] { "username", "email", "password" };
	}

	@Override
	public User login(String email, String password) {
		List<User> users = Query.table(this.table).select().where("email", "=", email).where("password", "=", password)
				.get(this.mapper);

		User user = (users != null && users.size() > 0) ? users.get(0) : null;

		return user;
	}

	@Override
	public void logout() {

	}

	public static void main(String[] args) {

	}

}

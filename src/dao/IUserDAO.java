package dao;

import bean.User;

public interface IUserDAO {
	public User login(String email, String password);
	public void logout();
}

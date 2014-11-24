package dao;

import java.util.List;

import entity.User;

public interface UserInterface {

	public abstract User getUser(String id);
	
	public abstract List<User> getAllUser();
	
	public abstract boolean saveUser(User user);
	
	public abstract boolean deleteUser(String id);
	
	public abstract boolean updateUser(User user);
	
}

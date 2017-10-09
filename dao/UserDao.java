package dao;

import java.util.Collection;
import java.util.List;

import domain.User;


public interface UserDao{
	
	public abstract User getUser(int id);
	
	public abstract void saveUser(User user);
	public abstract void updateUser(User user);
	public abstract User loginUser(String email,String password);
	public abstract String getUserIcon(int userid);
	public abstract void setUsrIcon(int userid,String icon);
}

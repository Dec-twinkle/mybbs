package dao;

import domain.User;



public class UserDaoImpl extends BaseDAO<User> implements UserDao{

	@Override
	public User getUser(int id) {
		String sql = "SELECT userid,name,password,email,phone,address,school,icon from user where userid=?";
		return query(sql, id);
	}

	@Override
	public void saveUser(User user) {
		String sql="INSERT INTO user (name,password,email,phone,address,school)values(?,?,?,?,?,?)";
		System.out.println(user);
		insert(sql, user.getName(),user.getPassword(),user.getEmail(),user.getPhone(),user.getAddress(),user.getSchool());
		
	}

	@Override
	public User loginUser(String email, String password) {
		String sql="SELECT userid,name,password,email,phone,address,school,icon from user where email=? and password=?";
		return query(sql,email,password);
	}

	@Override
	public void updateUser(User user) {
		String sql="UPDATE user SET name=?,password=?,email=?,phone=?,address=?,school=? WHERE userid=?";
		
		update(sql, user.getName(),user.getPassword(),user.getEmail(),user.getPhone(),user.getAddress(),user.getSchool(),user.getUserid());
		
		
	}

	@Override
	public String getUserIcon(int userid) {
		String sql="SELECT icon from user WHERE userid=?";
		return getSingleVal(sql, userid);
	}

	@Override
	public void setUsrIcon(int userid, String icon) {
		String sql="UPDATE user SET icon=? WHERE userid=?";
		update(sql, icon,userid);
	}

}

package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import user.User;

public class UserDao extends Dao {

	public UserDao() {
		super.connect();
	}

	public boolean addUser(User user) {
		// 남자면 FALSE(0) 여자면 TURE(1)
		int tempSex = user.isSex() == true ? 1 : 0;
		String sql = "INSERT INTO insurance.user(city,state, email, id, pw, job, name, sex, ssn, tel) VALUES(" + "'"
				+ user.getCity() + "', " + "'" + user.getState() + "', " + "'" + user.getEmail() + "', " + "'"
				+ user.getId() + "', " + "'" + user.getPw() + "', " + "'" + user.getJob() + "', " + "'" + user.getName()
				+ "', " + "'" + tempSex + "', " + "'" + user.getSsn() + "', " + "'" + user.getTel() + "');";
		return super.create(sql);
	}
	public User getUser(long idx) {
		String sql = "SELECT userIdx, email FROM insurance.user where userIdx = " + idx + ";";
		ResultSet rs = super.retrieve(sql);
		User user = new User();
		try {
			while(rs.next()) {
				String userIdx = rs.getString(1);
				String email = rs.getString(2);
				
				user.setUserIdx(Long.parseLong(userIdx));
				user.setEmail(email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	public List<User> getUserList() {
		String sql = "SELECT * FROM insurance.user";
		ResultSet rs = super.retrieve(sql);
		List<User> userLists = new ArrayList<>();
		boolean bolSex = true;
		try {
			while (rs.next()) {
				String userIdx = rs.getString(1);
				String city = rs.getString(2);
				String state = rs.getString(3);
				String email = rs.getString(4);
				String id = rs.getString(5);
				String pw = rs.getString(6);
				String job = rs.getString(7);
				String name = rs.getString(8);
				String sex = rs.getString(9);
				String ssn = rs.getString(10);
				String tel = rs.getString(11);

				if (sex.equals("0")) {
					bolSex = false;
				}
				User user = new User();
				user.setUserIdx(Long.parseLong(userIdx));
				user.setCity(city);
				user.setState(state);
				user.setEmail(email);
				user.setId(id);
				user.setPw(pw);
				user.setJob(job);
				user.setName(name);
				user.setSex(bolSex);
				user.setSsn(ssn);
				user.setTel(tel);
				userLists.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userLists;
	}

	public boolean deleteUser(Long userId) {
		String sql = "delete from insurance.user where userId=" + "'" + userId + "'";
		return super.delete(sql);
	}



}
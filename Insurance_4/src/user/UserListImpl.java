package user;

import java.util.List;

import dao.UserDao;


public class UserListImpl implements UserList{
 
	private UserDao userDao = new UserDao();

    public UserListImpl() {
    }

    @Override
	public boolean SignUp(User user) {
		return userDao.addUser(user);

	}

	@Override
	public boolean deleteUser(Long userIdx) {
		return userDao.deleteUser(userIdx);
	}
	
	@Override
	public List<User> getUserList() {
		List<User> userList = userDao.getUserList();
		return userList;
	}

	@Override
	public User getUser(long userIdx) {
		return userDao.getUser(userIdx);
	}
}
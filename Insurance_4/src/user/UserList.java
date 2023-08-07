package user;

 

import java.util.List;

public interface UserList {

	public boolean SignUp(User user);

	public boolean deleteUser(Long userIdx);

	public List<User> getUserList();

	public User getUser(long userIdx);

//	public boolean updateUser();
}
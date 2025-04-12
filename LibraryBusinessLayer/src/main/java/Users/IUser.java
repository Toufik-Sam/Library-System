package Users;

import java.util.List;

import UsersData.UserDTO;

public interface IUser {
	public UserDTO AddNewUser(UserDTO userDTO);
	public boolean UpdateUser(UserDTO userDTO);
	public UserDTO Find(int UserID);
	public UserDTO Find(String Email,String Password);
	public List<UserDTO>GetAllUsers();
	public boolean DeleteUser(int UserID);
	public boolean DoesUserExist(int UserID);
}

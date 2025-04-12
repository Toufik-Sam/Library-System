package UsersData;

import java.util.List;

public interface IUserData {
	public int AddNewUser(UserDTO userDTO);
	public boolean UpdateUser(UserDTO userDTO);
	public UserDTO GetUserInfo(int UserID);
	public UserDTO GetUserInfo(String Email,String Password);
	public List<UserDTO>GetAllUsers();
	public boolean DeleteUser(int UserID);
	public boolean DoesUserExist(int UserID);
}

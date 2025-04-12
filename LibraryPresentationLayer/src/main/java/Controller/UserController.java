package Controller;

import java.util.List;

import Users.IUser;
import UsersData.UserDTO;

public class UserController {
	private IUser _user;
	public UserController(IUser user) {
		this._user=user;
	}
	public boolean UpdateUserInfo(UserDTO userDTO) {
		return _user.UpdateUser(userDTO);
	}
	public UserDTO AddNewUser(UserDTO userDTO) {
		return _user.AddNewUser(userDTO);
	}
	public UserDTO Find(String Email,String PassWord) {
		return _user.Find(Email, PassWord);
	}
	public UserDTO Find(int UserID) {
		return _user.Find(UserID);
	}
	public boolean DeleteUser(int UserID) {
		return _user.DeleteUser(UserID);
	}
	public List<UserDTO>GetUsersList(){
		return _user.GetAllUsers();
	}
}

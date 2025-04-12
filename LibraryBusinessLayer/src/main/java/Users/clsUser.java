package Users;

import java.util.List;

import UsersData.IUserData;
import UsersData.UserDTO;

public class clsUser implements IUser{

	private IUserData _userData;
	public clsUser(IUserData userData) {
		this._userData=userData;
	}
	@Override
	public UserDTO AddNewUser(UserDTO userDTO) {
		int newID=_userData.AddNewUser(userDTO);
		return newID!=-1?new UserDTO(newID,userDTO.GetUserName(),userDTO.GetEmail(),userDTO.GetPassword()):null;
	}
	@Override
	public boolean UpdateUser(UserDTO userDTO) {
		return _userData.UpdateUser(userDTO);
	}
	
	@Override
	public boolean DeleteUser(int UserID) {
		return _userData.DeleteUser(UserID);
	}
	@Override
	public boolean DoesUserExist(int UserID) {
		return _userData.DoesUserExist(UserID); 
	}
	@Override
	public UserDTO Find(int UserID) {
		return _userData.GetUserInfo(UserID);
	}
	@Override
	public List<UserDTO> GetAllUsers() {
		return _userData.GetAllUsers();
	}
	@Override
	public UserDTO Find(String Email, String Password) {
		return _userData.GetUserInfo(Email, Password);
	}
}

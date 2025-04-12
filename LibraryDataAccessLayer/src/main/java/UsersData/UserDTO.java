package UsersData;

public class UserDTO {
	private int _UserID;
	private String _UserName;
	private String _Email;
	private String _Password;
	public UserDTO(int UserID,String UserName,String Email,String Password) {
		this._UserID=UserID;
		this._UserName=UserName;
		this._Email=Email;
		this._Password=Password;
	}
	public int GetUserID() {
	    return this._UserID; 
	}
    public void setUserID(int UserID) {
	    this._UserID = UserID;
	}
    public String GetUserName() {
    	return this._UserName;
    }
    public void SetUserName(String UserName) {
    	this._UserName=UserName;
    }
    public String GetEmail() {
    	return this._Email;
    }
    public void SetEmail(String Email) {
    	this._Email=Email;
    }
    public String GetPassword() {
    	return this._Password;
    } 
    public void SetPassword(String Password) {
    	this._Password=Password;
    }
}

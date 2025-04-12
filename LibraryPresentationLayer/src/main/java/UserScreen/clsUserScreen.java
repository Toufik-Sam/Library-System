package UserScreen;
import java.util.List;

import Controller.UserController;
import Global.CurrentUser;
import Main.clsScreenHeader;
import Users.clsUser;
import UsersData.UserDTO;
import UsersData.clsUserData;
import Utility.clsUtil;

public class clsUserScreen extends clsScreenHeader {
	private static void PrintUserCard() {
		System.out.println("\n==================================================\n");
		System.out.println("\t[1].User ID: "+Global.CurrentUser.GlobalUser.GetUserID());
		System.out.println("\t[2].UserName: "+Global.CurrentUser.GlobalUser.GetUserName());
		System.out.println("\t[2].Email: "+Global.CurrentUser.GlobalUser.GetEmail());
		System.out.println("\n==================================================\n");
	}
	private static void PrintUserCard(UserDTO user) {
		System.out.println("\n==================================================\n");
		System.out.println("\t[1].User ID: "+user.GetUserID());
		System.out.println("\t[2].UserName: "+user.GetUserName());
		System.out.println("\t[2].Email: "+user.GetEmail());
		System.out.println("\n==================================================\n");
	}
	private static void PrintUsersList(List<UserDTO>usersList) {
		for(int i=0;i<usersList.size();i++)
			PrintUserCard(usersList.get(i));
	}
	public static void PrintUserScreen() {
		PrintScreenHeader("Welcome To Library APP");
		System.out.println("\n==================================================");
		System.out.println("\n\t\t   [1].Sign in\n");
		System.out.println("\t\t   [2].Sign up");
		System.out.println("\n==================================================");
		System.out.println("Choose 1 to login and 2 to create a new account...");
	}
	public static void ShowUpdateMyInfoScreen(UserController userController) {
		clsUtil.ClearScreen();
		PrintScreenHeader("Edit Account");
		PrintUserCard();
		System.out.print("Enter Your UserName: ");
		String NewUserName=clsUtil.ReadString();
		System.out.print("\nEnter Your Email: ");
		String NewEmail=clsUtil.ReadString();
		System.out.print("\nEnter Your Password: ");
		String NewPassword=clsUtil.ReadString();
		//UserController userController=new UserController(new clsUser(new clsUserData()));
		if(userController.UpdateUserInfo(new UserDTO(Global.CurrentUser.GlobalUser.GetUserID(),
				NewUserName,NewEmail,NewPassword))) {
		  CurrentUser.GlobalUser=new UserDTO(Global.CurrentUser.GlobalUser.GetUserID(),
				  NewUserName,NewEmail,NewPassword);
		  System.out.println("Your Info were Updated Successfuly!");
		}
		else
			System.out.println("Update Failled! Try Again Later...");
	}
	public static void ShowUsersListScreen(UserController user) {
		clsUtil.ClearScreen();
		PrintScreenHeader("Users List");
		PrintUsersList(user.GetUsersList());
	}
	public static void ShowAddNewUerScreen(UserController user) {
		clsUtil.ClearScreen();
		PrintScreenHeader("Add New User");
		System.out.print("\nEnter New UserName:");
		String UserName=clsUtil.ReadString();
		System.out.print("Enter New User Email:");
		String Email=clsUtil.ReadString();
		System.out.print("Enter New Password:");
		String Password=clsUtil.ReadString();
		if(user.AddNewUser(new UserDTO(-1,UserName,Email,Password))!=null)
			System.out.println("New User Has Been Successfully Added!");
		else
			System.out.println("Error Pleas try again ...");
	}
	public static void ShowFindUserScreen(UserController user) {
		clsUtil.ClearScreen();
		PrintScreenHeader("Find a User");
		System.out.print("\n\nEnter UserID: ");
		int UserID=clsUtil.ReadShortNumber();
		UserDTO MyUser=user.Find(UserID);
		if(MyUser!=null)
			PrintUserCard(MyUser);
		else
			System.out.println("User Does not Exist or Was Not Found....");
		

	}
	public static void ShowDeleteUserScreen(UserController user) {
		clsUtil.ClearScreen();
		PrintScreenHeader("Delete a Book From Your Collection");
		PrintUsersList(user.GetUsersList());
		System.out.print("Enter The ID of The User That You want To Delete:");
		int UserID=clsUtil.ReadShortNumber();
		if(UserID==8)
			System.out.print("Admin Account Cannot be deleted!");
		if(user.DeleteUser(UserID)==true)
			System.out.println("User Was Deleted Successfully");
		else
			System.out.println("Error Try Again Later......");
	}
}

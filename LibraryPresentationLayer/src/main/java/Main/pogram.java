package Main;

import java.io.IOException;

import Books.clsBook;
import BooksData.clsBookData;
import BooksData.clsBookPageData;
import Controller.BookController;
import Controller.LibraryController;
import Controller.UserController;
import Libraries.LibraryDTO;
import Libraries.clsLibrary;
import Libraries.clsLibraryData;
import LibrariesBooks.clsLibraryBookData;
import UserScreen.clsUserScreen;
import Users.clsUser;
import UsersData.UserDTO;
import UsersData.clsUserData;
import Utility.clsUtil;

public class pogram {
	private static boolean _IsAdminFlag;
	private static boolean Login(UserController user) throws IOException {
		clsUtil.ClearScreen();
		System.out.print("Enter Your Email: ");
		String Email=clsUtil.ReadString();
		System.out.print("\nEnter Your Password: ");
		String Password=clsUtil.ReadString();
		Global.CurrentUser.GlobalUser=user.Find(Email, Password);
		if(Global.CurrentUser.GlobalUser!=null){
			if(Global.CurrentUser.GlobalUser.GetUserID()==8)
				_IsAdminFlag=true;
			return true;
		}
		System.out.println("Wrong Email Address or Password Pleas Try Again...");
		System.out.print("\nPress any key to go back to Home Page...\n");
	    System.in.read();
		return false;
	}
	private static void Register(UserController user,LibraryController library) throws IOException {
		clsUtil.ClearScreen();
		System.out.print("Enter Your UserName: ");
		String UserName=clsUtil.ReadString();
		System.out.print("Enter Your Email: ");
		String Email=clsUtil.ReadString();
		System.out.print("\nEnter Your Password: ");
		String Password=clsUtil.ReadString();
		
		if(user.AddNewUser(new UserDTO(-1,UserName,Email,Password))!=null) {
			System.out.print("You have been registered successfully !");
			System.out.print("\nPress any key to go back to Home Page...\n");
		    System.in.read();
		    return;
		}
		System.out.print("Failed To Register !");
		System.out.print("\nPress any key to go back to Home Page...\n");
	    System.in.read();
	}
	public static void main(String[] args) throws IOException {
		_IsAdminFlag=false;
		
		UserController user=new UserController(new clsUser(new clsUserData()));
		
		LibraryController library=new LibraryController(new clsLibrary(new clsLibraryData(),
				new clsLibraryBookData()));
		
		BookController book=new BookController(new clsBook(new clsBookData(),new clsBookPageData()));
		
		clsAdminMainScreen.library=library;
		clsAdminMainScreen.book=book;
		clsAdminMainScreen.user=user;
		
		clsMainScreen.library=library;
		clsMainScreen.book=book;
		clsMainScreen.user=user;
		
		Global.CurrentUser.GlobalUser=null;
		while(true) {
			clsUserScreen.PrintUserScreen();
			if(clsUtil.ReadShortNumberBetween((short)1,(short) 2)==1){
				if(!Login(user)){
					clsUtil.ClearScreen();
					continue;
				}
				else if(_IsAdminFlag){
					
					clsAdminMainScreen.ShowMainMenu();
					Global.CurrentUser.GlobalUser=null;
					clsUtil.ClearScreen();
					continue;
				}
				else {
					
					clsMainScreen.ShowMainMenu();
					Global.CurrentUser.GlobalUser=null;
					clsUtil.ClearScreen();
					continue;
				}
			}
			Register(user, library);
			clsUtil.ClearScreen();
		}
	}

}

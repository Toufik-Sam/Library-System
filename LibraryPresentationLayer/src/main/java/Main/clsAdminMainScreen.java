package Main;

import java.io.IOException;

import Controller.BookController;
import Controller.LibraryController;
import Controller.UserController;
import UserScreen.clsBookScreen;
import UserScreen.clsLibraryScreen;
import UserScreen.clsUserScreen;
import Utility.clsUtil;

public class clsAdminMainScreen extends clsScreenHeader{
	public static  LibraryController library;
	public static BookController book;
	public static UserController user;
	private enum enAdminMainMenuOptions{

		eUsersList(1),
		eAddNewUser(2),
	    eFindUser(3),
	    eDeleteUser(4),
	    eBooksList(5),
	    eAddNewBook(6),
	    eFindBook(7),
	    eDeleteBook(8),
	    eLogout(9);
	    private final int value; // Field to hold the associated value

	    // Constructor to initialize the value for each constant
	    enAdminMainMenuOptions(int value) {
	        this.value = value;
	    }

	    // Getter method to retrieve the associated value
	    public int getValue() {
	        return value;
	    }
	    public static enAdminMainMenuOptions fromShort(short value) {
	    	enAdminMainMenuOptions Res=enAdminMainMenuOptions.eLogout;
	        for (enAdminMainMenuOptions opt : enAdminMainMenuOptions.values()) {
	            if (opt.getValue() == value) {
	                Res= opt;
	                break;
	            }
	        }
	        return Res;
	};
	
	
}

    private static short _ReadMainMenueOption() {
	    short choice=clsUtil.ReadShortNumberBetween((short)1,(short)9);
	    return choice;
    }

    private static void _GoBackToMinMenu() throws IOException {
	    System.out.print("\nPress any key to go back to Admin Main Menu...\n");
        System.in.read(); // Waits for a key press
	    ShowMainMenu();
    }
    private static void _ShowUsersListMenu() {
    	clsUserScreen.ShowUsersListScreen(user);
    }
    private static void _ShowAddNewUserMenu() {
    	clsUserScreen.ShowAddNewUerScreen(user);
    }
    private static void _ShowFindUserMenu() {
    	clsUserScreen.ShowFindUserScreen(user);
    }
    private static void _ShowDeleteUserMenu() {
    	clsUserScreen.ShowDeleteUserScreen(user);
    }
    private static void _ShowBooksListMenu() {
    	clsBookScreen.ShowBooksListScreen(book);
    }
    private static void _ShowAddNewBookMenu() {
    	clsBookScreen.ShowAddNewBookScreen(book);
    }
    private static void _ShowFindBookMenu() {
    	clsBookScreen.ShowFindBookScreen(book);
    }
    private static void _ShowDeleteBookMenu() {
    	clsBookScreen.ShowDeleteBookScreen(book);
    }
    private static void _ShowExistMenu() {
    	return;
    }
    private static void _PerformMainOption(enAdminMainMenuOptions AdminMainMenuOption) throws IOException {
	switch(AdminMainMenuOption) {
	case eUsersList:{
		clsUtil.ClearScreen();
		_ShowUsersListMenu();
        _GoBackToMinMenu();
        break;
	}
	case eAddNewUser:{
		clsUtil.ClearScreen();
		_ShowAddNewUserMenu();
        _GoBackToMinMenu();
        break;
	}
	case eFindUser:{
		clsUtil.ClearScreen();
		_ShowFindUserMenu();
        _GoBackToMinMenu();
        break;
	}
	case eDeleteUser:{
		clsUtil.ClearScreen();
		_ShowDeleteUserMenu();
        _GoBackToMinMenu();
        break;
	}
	case eBooksList:{
		clsUtil.ClearScreen();
		_ShowBooksListMenu();
        _GoBackToMinMenu();
        break;
	}
	case eAddNewBook:{
		clsUtil.ClearScreen();
		_ShowAddNewBookMenu();
        _GoBackToMinMenu();
        break;
	}
	case eFindBook:{
		_ShowFindBookMenu();
        _GoBackToMinMenu();
        return;
	}
	case eDeleteBook:{
		_ShowDeleteBookMenu();
        _GoBackToMinMenu();
        return;
	}
	case eLogout:{
		clsUtil.ClearScreen();
		_ShowExistMenu();
		return;
	}
	}
    }
   public static void ShowMainMenu() throws IOException {
	clsUtil.ClearScreen();
	PrintScreenHeader("Main Menu");
	System.out.println("\n==================================================\n");
	System.out.println("\t[1].Users List\n");
	System.out.println("\t[2].Add New User\n");
	System.out.println("\t[3].Find User\n");
	System.out.println("\t[4].Delete User\n");
	System.out.println("\t[5].Books List\n");
	System.out.println("\t[6].Add New Book\n");
	System.out.println("\t[7].Find Book\n");
	System.out.println("\t[8].Delete Book\n");
	System.out.println("\t[9].Logout");
	System.out.println("\n==================================================\n");
	_PerformMainOption(enAdminMainMenuOptions.fromShort(_ReadMainMenueOption()));
   }
}

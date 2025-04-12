package Main;

import java.io.IOException;

import Controller.BookController;
import Controller.LibraryController;
import Controller.UserController;
import UserScreen.clsLibraryScreen;
import UserScreen.clsUserScreen;
import Utility.clsUtil;

public class clsMainScreen extends clsScreenHeader {
	public static  LibraryController library;
	public static BookController book;
	public static UserController user;
	private enum enMainMenuOptions{

		eReadBookFromMyLibrary(1),
		eListOfBooksInMyLibrary(2),
	    eAddNewBookToMyLibrary(3),
	    eDeleteBookFromMyLibrray(4),
	    eFindBookInMyLibrary(5),
	    eUpdateMyInfo(6),
	    eExit(7);
	    private final int value; // Field to hold the associated value

	    // Constructor to initialize the value for each constant
	    enMainMenuOptions(int value) {
	        this.value = value;
	    }

	    // Getter method to retrieve the associated value
	    public int getValue() {
	        return value;
	    }
	    public static enMainMenuOptions fromShort(short value) {
	    	enMainMenuOptions Res=enMainMenuOptions.eExit;
	        for (enMainMenuOptions opt : enMainMenuOptions.values()) {
	            if (opt.getValue() == value) {
	                Res= opt;
	                break;
	            }
	        }
	        return Res;
	};
	
	
}

    private static short _ReadMainMenueOption() {
	    short choice=clsUtil.ReadShortNumberBetween((short)1,(short)7);
	    return choice;
    }

    private static void _GoBackToMinMenu() throws IOException {
	    System.out.print("\nPress any key to go back to Main Menu...\n");
        System.in.read(); // Waits for a key press
	    ShowMainMenu();
    }
    private static void _ShowReadBookMenu() {
    clsLibraryScreen.ShowReadBookScreen(library,book);
    }
    private static void _ShowListOfBooksMenu() {
	clsLibraryScreen.ShowLibraryBooksListScreen(library);
    }
    private static void _ShowAddNewBookMenu() {
    clsLibraryScreen.ShowAddNewBookToLibrary(book,library);
    }
    private static void _ShowDeleteBookMenu() {
    clsLibraryScreen.ShowDeleteBookScreen(library,book);
    }
    private static void _ShowFindBookMenu() {
    clsLibraryScreen.ShowFindBookScreen(book); 
    }
    private static void _ShowUpdateMyInfoMenu() {
	clsUserScreen.ShowUpdateMyInfoScreen(user);
    }
    private static void _ShowExistMenu() {
	return;
    }
    private static void _PerformMainOption(enMainMenuOptions MainMenuOption) throws IOException {
	switch(MainMenuOption) {
	case eReadBookFromMyLibrary:{
		clsUtil.ClearScreen();
        _ShowReadBookMenu();
        _GoBackToMinMenu();
        break;
	}
	case eListOfBooksInMyLibrary:{
		clsUtil.ClearScreen();
        _ShowListOfBooksMenu();
        _GoBackToMinMenu();
        break;
	}
	case eAddNewBookToMyLibrary:{
		clsUtil.ClearScreen();
		_ShowAddNewBookMenu();
        _GoBackToMinMenu();
        break;
	}
	case eDeleteBookFromMyLibrray:{
		clsUtil.ClearScreen();
		_ShowDeleteBookMenu();
        _GoBackToMinMenu();
        break;
	}
	case eFindBookInMyLibrary:{
		clsUtil.ClearScreen();
		_ShowFindBookMenu();
        _GoBackToMinMenu();
        break;
	}
	case eUpdateMyInfo:{
		clsUtil.ClearScreen();
		_ShowUpdateMyInfoMenu();
        _GoBackToMinMenu();
        break;
	}
	case eExit:{
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
	System.out.println("\t[1].Read a Book\n");
	System.out.println("\t[2].Books Collection\n");
	System.out.println("\t[3].Add New Book\n");
	System.out.println("\t[4].Delete a Book\n");
	System.out.println("\t[5].Find a Book\n");
	System.out.println("\t[6].Edit Account\n");
	System.out.println("\t[7].Logout");
	System.out.println("\n==================================================\n");
	_PerformMainOption(enMainMenuOptions.fromShort(_ReadMainMenueOption()));
   }
}

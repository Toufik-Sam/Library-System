package UserScreen;

import java.util.List;

import Books.clsBook;
import BooksData.BookDTO;
import BooksData.BookPageDTO;
import BooksData.clsBookData;
import BooksData.clsBookPageData;
import Controller.BookController;
import Controller.LibraryController;
import Libraries.clsLibrary;
import Libraries.clsLibraryData;
import LibrariesBooks.LibraryBookDTO;
import LibrariesBooks.clsLibraryBookData;
import Main.clsScreenHeader;
import Utility.clsUtil;

public class clsLibraryScreen extends clsScreenHeader{
	private static void PrintBooks(List<BookDTO>LibraryBooks) {
		    System.out.println("\n\nBook List:");
	        System.out.println("==========================================================");
	        System.out.printf("%-10s %-30s %-20s%n", "Book ID", "Book Title", "Book Author");
	        System.out.println("==========================================================");

	        for (BookDTO book : LibraryBooks) {
	            System.out.printf("%-10d %-30s %-20s%n", book.GetBookID(), book.GetBookTitle(),
	            		book.GetBookAuthor());
	        }
	        System.out.println("==========================================================\n");
	}
	private static void PrintOneBook(BookDTO book) {
		System.out.println("\n\nBook Info:");
        System.out.println("==========================================================");
        System.out.printf("%-10s %-30s %-20s%n", "Book ID", "Book Title", "Book Author");
        System.out.println("==========================================================");
        
        System.out.printf("%-10d %-30s %-20s%n", book.GetBookID(), book.GetBookTitle(),
            		book.GetBookAuthor());
        System.out.println("==========================================================\n");
	}
	public static void ShowLibraryBooksListScreen(LibraryController library) {
		clsUtil.ClearScreen();
		PrintScreenHeader("Your Library Books Collection");
		//LibraryController library=new LibraryController(new clsLibrary(new clsLibraryData(),new clsLibraryBookData()));
        PrintBooks(library.GetLibraryBooks());
	}
	public static void ShowAddNewBookToLibrary(BookController book,LibraryController library) {
		clsUtil.ClearScreen();
		PrintScreenHeader("Add New Book To Your Library");
		//BookController book=new BookController(new clsBook(new clsBookData(),new clsBookPageData()));
		PrintBooks(book.GetAllBooks());
		//LibraryController library=new LibraryController(new clsLibrary(new clsLibraryData(),new clsLibraryBookData()));
        System.out.print("Enter the ID of the Book That You Want To Add to your Collection...");
        int LibraryID=library.GetUserLibraryID(Global.CurrentUser.GlobalUser.GetUserID());
        short BookID=clsUtil.ReadShortNumber();
        if(!book.DoesBookExist(BookID) || library.DoesBookExitInLibrary(LibraryID, BookID))
        {
            System.out.print("The Book That You have chosen Does not Exist or it may be already in your collection...");
            return;
        }
        
        if(library.AddNewBookToCollection(new LibraryBookDTO(-1,LibraryID,BookID,1)))
            System.out.print("The Book has Successfully been included to your Collection...");
        else
			System.out.println("Error Pleas try again ...");
	}
	public static void ShowFindBookScreen(BookController book) {
		clsUtil.ClearScreen();
		PrintScreenHeader("Find a Book in Stock");
		//BookController book=new BookController(new clsBook(new clsBookData(),new clsBookPageData()));
		System.out.print("\n\nEnter The Title of The Book That You Are Looking for: ");
		String Title=clsUtil.ReadString();
		BookDTO Mybook=book.Find(Title);
		if(Mybook!=null)
			PrintOneBook(Mybook);
		else
			System.out.print("Unfortunately we don't have this Book in Stock");
	}
	public static void ShowDeleteBookScreen(LibraryController library,BookController book) {
		clsUtil.ClearScreen();
		PrintScreenHeader("Delete a Book From Your Collection");
		//LibraryController library=new LibraryController(new clsLibrary(new clsLibraryData(),new clsLibraryBookData()));
		//BookController book=new BookController(new clsBook(new clsBookData(),new clsBookPageData()));
		PrintBooks(library.GetLibraryBooks());
        int LibraryID=library.GetUserLibraryID(Global.CurrentUser.GlobalUser.GetUserID());
		System.out.print("Enter The ID of the Book That You want to delete...");
		 short BookID=clsUtil.ReadShortNumber();
		 if(!book.DoesBookExist(BookID) || !library.DoesBookExitInLibrary(LibraryID, BookID)){
	            System.out.println("The Book That You have chosen Does not Exist or it has already been deleted...");
	            return;
	        }
		 if(library.DeleteBookFromLibrary(LibraryID, BookID))
	            System.out.println("The Book That you have selected has been successfully deleted...");
		 else
	            System.out.println("Error Try Again Later...");

	}
	public static void ShowReadBookScreen(LibraryController library,BookController book) {
		clsUtil.ClearScreen();
		PrintScreenHeader("Enjoy Reading!");
		//LibraryController library=new LibraryController(new clsLibrary(new clsLibraryData(),new clsLibraryBookData()));
		//BookController book=new BookController(new clsBook(new clsBookData(),new clsBookPageData()));
		PrintBooks(library.GetLibraryBooks());
        int LibraryID=library.GetUserLibraryID(Global.CurrentUser.GlobalUser.GetUserID());
		System.out.print("Enter The ID of the Book That You want to Read...");
		short BookID=clsUtil.ReadShortNumber();
		if(!book.DoesBookExist(BookID) || !library.DoesBookExitInLibrary(LibraryID, BookID)){
	            System.out.println("The Book That You have chosen Does not Exist in Your Collection...");
	            return;
	        }
		List<BookPageDTO>BookPages=book.GetBookPages(BookID);
		int Index=library.GetLastPage(LibraryID, BookID)-1;
		String Page="";
		String Response="";
		do {
			clsUtil.ClearScreen();
			PrintScreenHeader("Enjoy Reading!");
			System.out.println("Page:"+Index+1);
			Page=BookPages.get(Index).GetPageContent();
			System.out.println("\n"+clsUtil.wrapText(Page, 80)+"\n");
			System.out.print("Enter NEXT to go to next page and BACK to go to the previous Page"
					+"Otherwise Enter EXIT to go to Main Menu"+ "\n");
			Response=clsUtil.ReadString();
			if(clsUtil.compareStrings(Response, "Next") && Index<BookPages.size()-1)
				Index++;
			else if(clsUtil.compareStrings(Response, "Back") && Index>0)
				Index--;
			else if(clsUtil.compareStrings(Response, "Exit"))
					break;
			
		}while(true);
		library.UpdateBookLastPageInLibrary(LibraryID, BookID, Index+1);
	}
	
}

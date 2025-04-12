package UserScreen;

import java.util.ArrayList;
import java.util.List;

import BooksData.BookDTO;
import BooksData.BookPageDTO;
import BooksData.FullBookDTO;
import Controller.BookController;
import Main.clsScreenHeader;
import Utility.clsUtil;

public class clsBookScreen extends clsScreenHeader {
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
	public static void ShowBooksListScreen(BookController book) {
		clsUtil.ClearScreen();
		PrintScreenHeader("Books in Stock");
		PrintBooks(book.GetAllBooks());
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
	public static void ShowDeleteBookScreen(BookController book) {
		clsUtil.ClearScreen();
		PrintScreenHeader("Delete a Book From Stock");
		PrintBooks(book.GetAllBooks());
		System.out.print("Enter The ID of the Book That You want to delete...");
		short BookID=clsUtil.ReadShortNumber();
		 if(book.DeleteBook(BookID))
	            System.out.println("The Book That you have selected has been successfully deleted...");
		 else
	            System.out.println("Error Try Again Later...");
	}
	public static void ShowAddNewBookScreen(BookController book) {
		clsUtil.ClearScreen();
		PrintScreenHeader("Add New Book to Stock");
		System.out.print("\nEnter Book Title: ");
		String BookTitle=clsUtil.ReadString();
		System.out.print("Enter Book Author: ");
		String BookAuthor=clsUtil.ReadString();
		List<BookPageDTO>BookPages=new ArrayList<>();
		System.out.print("Enter how many pages does the book have : ");
		int BookPageCount=clsUtil.ReadShortNumber();
		int i=1;
		while(BookPageCount>0) {
			System.out.print("Enter Book Page "+i+" Content: ");
			String PageContent=clsUtil.ReadString();
			BookPages.add(new BookPageDTO(-1,-1,PageContent,i+1));
			BookPageCount--;
			i++;
		}
		FullBookDTO fullBookDTO=new FullBookDTO(new BookDTO(-1,BookTitle,BookAuthor),BookPages);
		if(book.AddNewBook(fullBookDTO))
			System.out.println("Book Was Added Successfully");
		else
			System.out.println("Error Try Again Later...");
	}
}

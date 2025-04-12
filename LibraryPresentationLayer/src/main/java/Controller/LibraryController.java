package Controller;

import java.util.List;

import BooksData.BookDTO;
import Libraries.ILibrary;
import Libraries.LibraryDTO;
import LibrariesBooks.LibraryBookDTO;

public class LibraryController {
	private ILibrary _library;
	public LibraryController(ILibrary library) {
		this._library=library;
	}
	public List<BookDTO>GetLibraryBooks(){
		return _library.GetLibraryBooks(_library.FindUserLibraryID(Global.CurrentUser.
				GlobalUser.GetUserID())); 
	}
	public boolean AddNewBookToCollection(LibraryBookDTO librarybookDTO) {
		return _library.AddNewLibraryBook(librarybookDTO)!=null?true:false;
	}
	public int GetUserLibraryID(int UserID) {
		return _library.FindUserLibraryID(Global.CurrentUser.GlobalUser.GetUserID());
	}
	public boolean DoesBookExitInLibrary(int LibraryID,int BookID) {
		return _library.DoesBookExistInLibrary(LibraryID, BookID);
	}
	public boolean DeleteBookFromLibrary(int LibraryID,int BookID) {
		return _library.DeleteLibraryBook(LibraryID, BookID);
	}
	public boolean UpdateBookLastPageInLibrary(int Library,int BookID,int PageNum) {
		return _library.UpdateLastPage(Library, BookID, PageNum);
	}
	public int GetLastPage(int LibraryID,int BookID) {
		return _library.GetLastPageFromLibraryBook(LibraryID, BookID);
	}
	public boolean AddNewLibrary(LibraryDTO libraryDTO) {
		return _library.AddNewLibrary(libraryDTO)!=null;
	}
}

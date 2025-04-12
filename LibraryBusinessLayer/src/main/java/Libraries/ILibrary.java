package Libraries;

import java.util.List;

import BooksData.BookDTO;
import LibrariesBooks.LibraryBookDTO;

public interface ILibrary {
	public LibraryDTO AddNewLibrary(LibraryDTO libraryDTO);
	public boolean UpdateLibrary(LibraryDTO libraryDTO);
	public boolean UpdateLastPage(int LibraryID,int BookID,int PageNumber);
	public boolean DeleteLibrary(int LibraryID);
	public boolean DoesLibraryExist(int LibraryID);
	public LibraryDTO Find(int LibraryID);
	public int FindUserLibraryID(int UserID);
	public List<LibraryDTO>GetAllLibraries();
	public LibraryBookDTO AddNewLibraryBook(LibraryBookDTO libraryBookDTO);
	public boolean DeleteLibraryBook(int LibraryBookID);
	public boolean DeleteLibraryBook(int LibraryID,int BookID);
	public List<BookDTO>GetLibraryBooks(int LibraryID);	
	public boolean DoesBookExistInLibrary(int LibraryID,int BookID);
	public int GetLastPageFromLibraryBook(int LibraryID,int BookID);

}

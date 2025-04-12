package LibrariesBooks;

import java.util.List;

import BooksData.BookDTO;

public interface ILibraryBookData {
	public int AddNewLibraryBook(LibraryBookDTO libraryBookDTO);
	public boolean UpdateLastPage(int LibraryID,int BookID,int PageNumber);
	public boolean DeleteLibraryBook(int LibraryBookID);
	public boolean DeleteLibraryBook(int LibraryID,int BookID);
	public List<BookDTO>GetLibraryBooks(int LibraryID);	
	public boolean DoesBookExistInLibrary(int LibraryID,int BookID);
	public int GetLastPageFromLibraryBook(int LibraryID,int BookID);
}

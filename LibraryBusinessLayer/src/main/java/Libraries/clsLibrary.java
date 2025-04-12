package Libraries;

import java.util.List;

import LibrariesBooks.ILibraryBookData;
import LibrariesBooks.LibraryBookDTO;
import BooksData.BookDTO;

public class clsLibrary implements ILibrary{
	private ILibraryData _libraryData;
	private ILibraryBookData _libraryBookData;
	public clsLibrary(ILibraryData libraryData,ILibraryBookData libraryBookData) {
		this._libraryData=libraryData;
		this._libraryBookData=libraryBookData;
	}
	@Override
	public LibraryDTO AddNewLibrary(LibraryDTO libraryDTO) {
		int newID=_libraryData.AddNewLibrary(libraryDTO);
		return newID!=-1?new LibraryDTO(newID,libraryDTO.GetUserID()):null;
	}

	@Override
	public boolean UpdateLibrary(LibraryDTO libraryDTO) {
		return _libraryData.UpdateLibrary(libraryDTO);
	}

	@Override
	public boolean DeleteLibrary(int LibraryID) {
		return _libraryData.DeleteLibrary(LibraryID);
	}

	@Override
	public boolean DoesLibraryExist(int LibraryID) {
		return _libraryData.DoesLibraryExist(LibraryID);
	}

	@Override
	public LibraryDTO Find(int LibraryID) {
		return _libraryData.GetLibraryByID(LibraryID);
	}

	@Override
	public List<LibraryDTO> GetAllLibraries() {
		return _libraryData.GetAllLibraries();
	}
	@Override
	public int FindUserLibraryID(int UserID) {
		return _libraryData.GetUserLibraryID(UserID);
	}
	@Override
	public LibraryBookDTO AddNewLibraryBook(LibraryBookDTO libraryBookDTO) {
		int newID=_libraryBookData.AddNewLibraryBook(libraryBookDTO);
		return newID!=-1?new LibraryBookDTO(newID,libraryBookDTO.GetLibraryID(),libraryBookDTO.GetBookID(),
				libraryBookDTO.GetCurrentPage()):null;
	}
	@Override
	public boolean DeleteLibraryBook(int LibraryBookID) {
		return _libraryBookData.DeleteLibraryBook(LibraryBookID);
	}
	@Override
	public List<BookDTO> GetLibraryBooks(int LibraryID) {
		return _libraryBookData.GetLibraryBooks(LibraryID);
	}
	@Override
	public boolean DoesBookExistInLibrary(int LibraryID, int BookID) {
		return _libraryBookData.DoesBookExistInLibrary(LibraryID, BookID);
	}
	@Override
	public boolean DeleteLibraryBook(int LibraryID, int BookID) {
		return _libraryBookData.DeleteLibraryBook(LibraryID, BookID);
	}
	@Override
	public boolean UpdateLastPage(int LibraryID, int BookID, int PageNumber) {
		return _libraryBookData.UpdateLastPage(LibraryID, BookID, PageNumber);
	}
	@Override
	public int GetLastPageFromLibraryBook(int LibraryID, int BookID) {
		return _libraryBookData.GetLastPageFromLibraryBook(LibraryID, BookID);
	}

}

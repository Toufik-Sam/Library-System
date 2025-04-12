package Books;

import java.util.List;

import BooksData.BookDTO;
import BooksData.BookPageDTO;
import BooksData.FullBookDTO;
import BooksData.IBookPageData;
import BooksData.IBookData;


public class clsBook implements IBook {
	private IBookData _bookData;
	private IBookPageData _bookPageData;
	public clsBook(IBookData bookData,IBookPageData bookpageData) {
		this._bookData=bookData;
		this._bookPageData=bookpageData;
	}
	@Override
	public BookDTO AddNewBook(FullBookDTO fullBookDTO) {
		int newID=_bookData.AddNewBook(fullBookDTO);
		return newID!=-1?new BookDTO(newID,fullBookDTO.GetBook().GetBookTitle(),
				fullBookDTO.GetBook().GetBookAuthor()):null; 
	}

	@Override
	public boolean UpdateBook(BookDTO bookDTO) {
		return _bookData.UpdateBook(bookDTO);
	}

	@Override
	public BookDTO Find(int BookID) {
		return _bookData.GetBookByID(BookID);
	}

	@Override
	public List<BookDTO> GetAllBooks() {
		return _bookData.GetAllBooks();
	}

	@Override
	public boolean DeleteBook(int BookID) {
		return _bookData.DeleteBook(BookID);
	}

	@Override
	public boolean DoesBookExist(int BookID) {
		return _bookData.DoesBookExist(BookID);
	}
	@Override
	public List<BookPageDTO> GetBookPages(int BookID) {
		return _bookPageData.GetAllBookPages(BookID);
	}
	@Override
	public BookDTO Find(String Title) {
		return _bookData.GetBookByTitle(Title);
	}

}

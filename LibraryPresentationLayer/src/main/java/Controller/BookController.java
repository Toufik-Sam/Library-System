package Controller;

import java.util.List;

import Books.IBook;
import BooksData.BookDTO;
import BooksData.BookPageDTO;
import BooksData.FullBookDTO;

public class BookController {
	private IBook _book;
	public BookController(IBook book) {
		this._book=book;
	}
	public List<BookDTO>GetAllBooks(){
		return _book.GetAllBooks();
	}
	public boolean DoesBookExist(int BookID) {
		return _book.DoesBookExist(BookID);
	}
	public BookDTO Find(int BookID) {
		return _book.Find(BookID);
	}
	public BookDTO Find(String Title){
		return _book.Find(Title);
	}
	public List<BookPageDTO>GetBookPages(int BookID){
		return _book.GetBookPages(BookID);
	}
	public boolean DeleteBook(int BookID){
		return _book.DeleteBook(BookID);
	}
	public boolean AddNewBook(FullBookDTO fullbookDTO) {
		return _book.AddNewBook(fullbookDTO)!=null;
	}
}

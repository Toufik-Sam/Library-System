package Books;

import java.util.List;

import BooksData.BookDTO;
import BooksData.BookPageDTO;
import BooksData.FullBookDTO;

public interface IBook {
	public BookDTO AddNewBook(FullBookDTO fullBookDTO);
	public boolean UpdateBook(BookDTO bookDTO);
	public BookDTO Find(int BookID);
	public BookDTO Find(String Title);
	public List<BookDTO>GetAllBooks();
	public boolean DeleteBook(int BookID);
	public boolean DoesBookExist(int BookID);
	public List<BookPageDTO>GetBookPages(int BookID);
}

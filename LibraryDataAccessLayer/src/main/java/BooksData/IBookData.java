package BooksData;

import java.util.List;

public interface IBookData {
	public int AddNewBook(FullBookDTO fullBook);
	public boolean UpdateBook(BookDTO bookDTO);
	public boolean DeleteBook(int BookID);
	public BookDTO GetBookByID(int BookID);
	public BookDTO GetBookByTitle(String Title);
	public List<BookDTO>GetAllBooks();
	public boolean DoesBookExist(int BookID); 
}

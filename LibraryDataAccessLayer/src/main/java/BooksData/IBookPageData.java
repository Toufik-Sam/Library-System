package BooksData;

import java.util.List;

public interface IBookPageData {
	public int AddNewBookPage(BookPageDTO bookpage);
	public boolean Updatebookpage(BookPageDTO bookpage);
	public boolean DeleteBookPage(int BookPageID);
	public BookPageDTO GetBookPageByID(int BookPageID);
	public List<BookPageDTO>GetAllBookPages(int BookID);
	public boolean DoesBookPageExist(int BookPageID);
}

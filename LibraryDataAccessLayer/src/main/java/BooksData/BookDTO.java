package BooksData;

public class BookDTO {
	private int _BookID;
	private String _BookTitle;
	private String _BookAuthor;
	public BookDTO(int BookID,String BookTitle,String BookAuthor) {
		this._BookID=BookID;
		this._BookTitle=BookTitle; 
		this._BookAuthor=BookAuthor;
	}
	public void SetBookID(int BookID) {
		this._BookID=BookID;
	}
	public int GetBookID() {
		return this._BookID;
	}
	public void SetBookTitle(String BookTitle) {
		this._BookTitle=BookTitle;
	}
	public String GetBookTitle() {
		return this._BookTitle;
	}
	public void SetBookAuthor(String BookAuthor) {
		this._BookAuthor=BookAuthor;
	}
	public String GetBookAuthor(){
		return this._BookAuthor;
	}
}

package BooksData;

public class BookPageDTO {
	private int _BookPageID;
	private int _BookID;
	private String _PageContent;
	private int _PageNumber;
	public BookPageDTO(int BookPageID,int BookID,String PageContent,int PageNumber){
		this._BookPageID=BookPageID;
		this._BookID=BookID;
		this._PageContent=PageContent;
		this._PageNumber=PageNumber;
	}
	public void SetBookPageID(int BookPageID) {
		this._BookPageID=BookPageID;
	}
	public int GetBookPageID() {
		return this._BookPageID;
	}
	public void SetBookID(int BookID) {
		this._BookID=BookID;
	}
	public int GetBookID() {
		return this._BookID;
	}
	public void SetPageContent(String PageContent) {
		this._PageContent=PageContent;
	}
	public String GetPageContent() {
		return this._PageContent;
	}
	public void SetPageNumber(int PageNumber) {
		this._PageNumber=PageNumber;
	}
	public int GetPageNumber() {
		return this._PageNumber;
	}
}

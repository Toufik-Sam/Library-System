package LibrariesBooks;

public class LibraryBookDTO {
	private int _LibraryBookID;
	private int _LibraryID;
	private int _BookID;
	private int _CurrentPage;
	public LibraryBookDTO(int LibraryBookID,int LibraryID,int BookID,int CurrentPage) {
		this._LibraryBookID=LibraryBookID;
		this._LibraryID=LibraryID;
		this._BookID=BookID;
		this._CurrentPage=CurrentPage;
	}
	public void SetLibraryBookID(int LibraryBookID) {
		this._LibraryBookID=LibraryBookID;
	}
	public int GetLibraryBookID() {
		return this._LibraryBookID;
	}
	public void SetLibraryID(int LibraryID) {
		this._LibraryID=LibraryID;
	}
	public int GetLibraryID() {
		return this._LibraryID;
	}
	public void SetBookID(int BookID) {
		this._BookID=BookID;
	}
	public int GetBookID() {
		return this._BookID;
	}
	public void SetCurrentPage(int CurrentPage) {
		this._CurrentPage=CurrentPage;
	}
	public int GetCurrentPage() {
		return this._CurrentPage;
	}
}

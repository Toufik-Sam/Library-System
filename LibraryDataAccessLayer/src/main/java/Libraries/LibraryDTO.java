package Libraries;

public class LibraryDTO {
	private int _LibarayID;
	private int _UserID;
	public LibraryDTO(int LibraryID,int UserID) {
		this._UserID=UserID;
		this._LibarayID=LibraryID;
	}
	public void SetLibraryID(int LibraryID) {
		this._LibarayID=LibraryID;
	}
	public int GetLibraryID() {
		return this._LibarayID;
	} 
	public void SetUserID(int UserID) {
		this._UserID=UserID;
	}
	public int GetUserID() {
		return this._UserID;
	} 
}

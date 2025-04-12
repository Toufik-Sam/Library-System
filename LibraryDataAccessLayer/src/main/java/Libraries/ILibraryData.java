package Libraries;

import java.util.List;

public interface ILibraryData {
	public int AddNewLibrary(LibraryDTO libraryDTO);
	public boolean UpdateLibrary(LibraryDTO libraryDTO);
	public boolean DeleteLibrary(int LibraryID);
	public boolean DoesLibraryExist(int LibraryID);
	public LibraryDTO GetLibraryByID(int LibraryID);
	public int GetUserLibraryID(int UserID);
	public List<LibraryDTO>GetAllLibraries();
}

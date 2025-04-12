package LibrariesBooks;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BooksData.BookDTO;
import Settings.ConnectionSettings;

public class clsLibraryBookData implements ILibraryBookData{

	@Override
	public int AddNewLibraryBook(LibraryBookDTO libraryBookDTO) {
		 Connection connection = null;
	        CallableStatement callableStatement = null;
	        int result = -1; // Default value for return

	        try {
	            // Establish connection to the database
	            connection = DriverManager.getConnection(ConnectionSettings.dbURL, ConnectionSettings.dbUserName, 
	            		ConnectionSettings.dbPassword);

	            // Prepare the stored procedure call
	            String sql = "{call dbo.spAddNewLibraryBook(?, ?, ?, ?)}"; // Syntax for a stored procedure with a return value
	            callableStatement = connection.prepareCall(sql);

	            // Set input parameters (index 2 and 3)
	            callableStatement.setInt(1, libraryBookDTO.GetLibraryID()); // Example: first input parameter
	            callableStatement.setInt(2, libraryBookDTO.GetBookID());// Example: second input parameter
	            callableStatement.setInt(3, libraryBookDTO.GetCurrentPage());
	            // Register the return parameter (index 1)
	            callableStatement.registerOutParameter(4, java.sql.Types.INTEGER);

	            // Execute the stored procedure
	            callableStatement.execute();

	            // Retrieve the return value
	            result = callableStatement.getInt(4);

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (callableStatement != null) callableStatement.close();
	                if (connection != null) connection.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }

	        return result;
	}

	@Override
	public boolean DeleteLibraryBook(int LibraryBookID) {
		Connection connection = null;
        CallableStatement callableStatement = null;
        boolean RowsAffected = false; // Default value for return

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, ConnectionSettings.dbUserName, 
            		ConnectionSettings.dbPassword);

            // Prepare the stored procedure call
            String sql = "{call dbo.spDeleteLibraryBook(?, ?)}"; // Syntax for a stored procedure with a return value
            callableStatement = connection.prepareCall(sql);

            // Set input parameters (index 2 and 3)
            callableStatement.setInt(1, LibraryBookID);
            // Register the return parameter (index 1)
            callableStatement.registerOutParameter(2, java.sql.Types.BIT);


            // Execute the stored procedure
            callableStatement.execute();

            // Retrieve the return value
            RowsAffected = callableStatement.getBoolean(2);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

	return RowsAffected;
	}

	@Override
	public List<BookDTO> GetLibraryBooks(int LibraryID) {
		 Connection connection = null;
	        CallableStatement callableStatement = null;
	        ResultSet resultSet = null;
	        List<BookDTO> booksList = new ArrayList<>();

	        try {
	            // Establish connection to the database
	            connection = DriverManager.getConnection(ConnectionSettings.dbURL, 
	            		ConnectionSettings.dbUserName, ConnectionSettings.dbPassword); 

	            // Prepare the stored procedure call
	            String sql = "{call dbo.spGetLibraryBooks(?)}";
	            callableStatement = connection.prepareCall(sql);
	            callableStatement.setInt(1, LibraryID);
	            // Execute the stored procedure
	            callableStatement.execute();
	            // Get the result set
	            resultSet = callableStatement.getResultSet();

	            // Process the result set
	            while (resultSet.next()) {
	            	booksList.add(new BookDTO(resultSet.getInt("BookID"),
 			                              resultSet.getString("BookTitle"),
 			                              resultSet.getString("BookAuthor")));
	            }

	        } catch (SQLException e) { 
	            e.printStackTrace();
	        } finally {
	            try {
	                if (resultSet != null) resultSet.close();
	                if (callableStatement != null) callableStatement.close();
	                if (connection != null) connection.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }

	        return booksList;
	}

	@Override
	public boolean DoesBookExistInLibrary(int LibraryID, int BookID) {
		Connection connection = null;
        CallableStatement callableStatement = null;
        boolean Found=false; // Default value for return

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, ConnectionSettings.dbUserName, 
            		ConnectionSettings.dbPassword);

            // Prepare the stored procedure call
            String sql = "{call dbo.spDoesBookExistInLibrary(?, ?, ?)}"; // Syntax for a stored procedure with a return value
            callableStatement = connection.prepareCall(sql);

            // Set input parameters (index 2 and 3)
            callableStatement.setInt(1, LibraryID);
            callableStatement.setInt(2, BookID);
            // Register the return parameter (index 1)
            callableStatement.registerOutParameter(3, java.sql.Types.BIT);


            // Execute the stored procedure
            callableStatement.execute();

            // Retrieve the return value
            Found = callableStatement.getBoolean(3);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

	return Found;
	}

	@Override
	public boolean DeleteLibraryBook(int LibraryID, int BookID) {
		Connection connection = null;
        CallableStatement callableStatement = null;
        boolean RowsAffected = false; // Default value for return

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, ConnectionSettings.dbUserName, 
            		ConnectionSettings.dbPassword);

            // Prepare the stored procedure call
            String sql = "{call dbo.spDeleteBookFromLibrary(?, ?, ?)}"; // Syntax for a stored procedure with a return value
            callableStatement = connection.prepareCall(sql);

            // Set input parameters (index 2 and 3)
            callableStatement.setInt(1, LibraryID);
            callableStatement.setInt(2, BookID);

            // Register the return parameter (index 1)
            callableStatement.registerOutParameter(3, java.sql.Types.BIT);


            // Execute the stored procedure
            callableStatement.execute();

            // Retrieve the return value
            RowsAffected = callableStatement.getBoolean(3);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

	return RowsAffected;
	}

	@Override
	public boolean UpdateLastPage(int LibraryID, int BookID, int PageNumber) {
	    Connection connection = null;
        CallableStatement callableStatement = null;
        boolean RowsAffected = false; // Default value for return

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, ConnectionSettings.dbUserName, 
            		ConnectionSettings.dbPassword);

            // Prepare the stored procedure call
            String sql = "{call dbo.spUpdateLastPage(?, ?, ?, ?)}"; // Syntax for a stored procedure with a return value
            callableStatement = connection.prepareCall(sql);

            // Set input parameters (index 2 and 3)
            callableStatement.setInt(1,LibraryID);
            callableStatement.setInt(2,BookID); // Example: first input parameter
            callableStatement.setInt(3,PageNumber); // Example: second input parameter
            // Register the return parameter (index 1)
            callableStatement.registerOutParameter(4, java.sql.Types.BIT);


            // Execute the stored procedure
            callableStatement.execute();

            // Retrieve the return value
            RowsAffected = callableStatement.getBoolean(4);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

	return RowsAffected;
	}

	@Override
	public int GetLastPageFromLibraryBook(int LibraryID, int BookID) {
		Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        int LastPage=-1;

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, 
            		ConnectionSettings.dbUserName, ConnectionSettings.dbPassword); 

            // Prepare the stored procedure call
            String sql = "{call dbo.spGetLastPageFromLibraryBook(?,?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, LibraryID);
            callableStatement.setInt(2, BookID);
            // Execute the stored procedure
            callableStatement.execute();

            // Get the result set
            resultSet = callableStatement.getResultSet();

            // Process the result set
            while (resultSet.next()) {
            	LastPage=resultSet.getInt("CurrentPage");
            }

        } catch (SQLException e) { 
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return LastPage;
	}
}

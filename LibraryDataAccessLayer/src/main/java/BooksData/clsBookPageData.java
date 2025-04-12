package BooksData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Settings.ConnectionSettings;

public class clsBookPageData implements IBookPageData {

	@Override
	public int AddNewBookPage(BookPageDTO bookpage) {
		Connection connection = null;
        CallableStatement callableStatement = null;
        int result = -1; // Default value for return

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, ConnectionSettings.dbUserName, 
            		ConnectionSettings.dbPassword);

            // Prepare the stored procedure call
            String sql = "{call dbo.spAddNewBookPage(?, ?, ?, ?)}"; // Syntax for a stored procedure with a return value
            callableStatement = connection.prepareCall(sql);

            // Set input parameters (index 2 and 3)
            callableStatement.setInt(1, bookpage.GetBookID()); // Example: first input parameter
            callableStatement.setString(2, bookpage.GetPageContent()); // Example: second input parameter
            callableStatement.setInt(3, bookpage.GetPageNumber()); // Example: first input parameter

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
	public boolean Updatebookpage(BookPageDTO bookpage) {
		Connection connection = null;
        CallableStatement callableStatement = null;
        boolean RowsAffected = false; // Default value for return

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, ConnectionSettings.dbUserName, 
            		ConnectionSettings.dbPassword);

            // Prepare the stored procedure call
            String sql = "{call dbo.spUpdateBookPage(?, ?, ?, ?, ?)}"; // Syntax for a stored procedure with a return value
            callableStatement = connection.prepareCall(sql);

            // Set input parameters (index 2 and 3)
            callableStatement.setInt(1,bookpage.GetBookPageID());
            callableStatement.setInt(2,bookpage.GetBookID());
            callableStatement.setString(3,bookpage.GetPageContent()); // Example: first input parameter
            callableStatement.setInt(4,bookpage.GetPageNumber()); // Example: second input parameter
            // Register the return parameter (index 1)
            callableStatement.registerOutParameter(5, java.sql.Types.BIT);


            // Execute the stored procedure
            callableStatement.execute();

            // Retrieve the return value
            RowsAffected = callableStatement.getBoolean(5);

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
	public boolean DeleteBookPage(int BookPageID) {
		 Connection connection = null;
	        CallableStatement callableStatement = null;
	        boolean RowsAffected = false; // Default value for return

	        try {
	            // Establish connection to the database
	            connection = DriverManager.getConnection(ConnectionSettings.dbURL, ConnectionSettings.dbUserName, 
	            		ConnectionSettings.dbPassword);

	            // Prepare the stored procedure call
	            String sql = "{call dbo.spDeleteBookPage(?, ?)}"; // Syntax for a stored procedure with a return value
	            callableStatement = connection.prepareCall(sql);

	            // Set input parameters (index 2 and 3)
	            callableStatement.setInt(1, BookPageID);
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
	public BookPageDTO GetBookPageByID(int BookPageID) {
		Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        BookPageDTO bookPageDTO=null;

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, 
            		ConnectionSettings.dbUserName, ConnectionSettings.dbPassword); 

            // Prepare the stored procedure call
            String sql = "{call dbo.spGetBookPageByID(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, BookPageID);
            // Execute the stored procedure
            callableStatement.execute();

            // Get the result set
            resultSet = callableStatement.getResultSet();

            // Process the result set
            while (resultSet.next()) {
            	bookPageDTO=new BookPageDTO(resultSet.getInt("BookPageID"),
            			            resultSet.getInt("BookID"),
            			            resultSet.getString("PageContent"),
            			            resultSet.getInt("PageNumber"));
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

        return bookPageDTO;
	}

	@Override
	public List<BookPageDTO> GetAllBookPages(int BookID) {
		    Connection connection = null;
	        CallableStatement callableStatement = null;
	        ResultSet resultSet = null;
	        List<BookPageDTO> bookPagesList = new ArrayList<>();

	        try {
	            // Establish connection to the database
	            connection = DriverManager.getConnection(ConnectionSettings.dbURL, 
	            		ConnectionSettings.dbUserName, ConnectionSettings.dbPassword); 

	            // Prepare the stored procedure call
	            String sql = "{call dbo.spGetAllBookPages(?)}";
	            callableStatement = connection.prepareCall(sql);
	            callableStatement.setInt(1, BookID);
	            // Execute the stored procedure
	            callableStatement.execute();

	            // Get the result set
	            resultSet = callableStatement.getResultSet();

	            // Process the result set
	            while (resultSet.next()) {
	            	bookPagesList.add(new BookPageDTO(resultSet.getInt("BookPageID"),
    			                                      resultSet.getInt("BookID"),
    			                                      resultSet.getString("PageContent"),
    			                                      resultSet.getInt("PageNumber"))); 
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

	        return bookPagesList;
	}

	@Override
	public boolean DoesBookPageExist(int BookPageID) {
		Connection connection = null;
        CallableStatement callableStatement = null;
        boolean Found=false; // Default value for return

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, ConnectionSettings.dbUserName, 
            		ConnectionSettings.dbPassword);

            // Prepare the stored procedure call
            String sql = "{call dbo.spDoesBookPageExist(?, ?)}"; // Syntax for a stored procedure with a return value
            callableStatement = connection.prepareCall(sql);

            // Set input parameters (index 2 and 3)
            callableStatement.setInt(1, BookPageID);
            // Register the return parameter (index 1)
            callableStatement.registerOutParameter(2, java.sql.Types.BIT);


            // Execute the stored procedure
            callableStatement.execute();

            // Retrieve the return value
            Found = callableStatement.getBoolean(2);

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

}

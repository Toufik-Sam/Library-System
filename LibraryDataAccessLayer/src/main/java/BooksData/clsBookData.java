package BooksData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;

import Settings.ConnectionSettings;

public class clsBookData implements IBookData {

	@Override
	public int AddNewBook(FullBookDTO fullBookDTO) {
        Connection connection = null;
        SQLServerCallableStatement callableStatement = null;
        int result = -1; // Default value for return

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, 
            		ConnectionSettings.dbUserName, ConnectionSettings.dbPassword);
            //Create Table 
            SQLServerDataTable  table = new SQLServerDataTable ();
            table.addColumnMetadata("BookID",Types.INTEGER);
            table.addColumnMetadata("PageContent",Types.NVARCHAR);
            table.addColumnMetadata("PageNumber",Types.INTEGER);
            //Populate The Table
            for(BookPageDTO bookPage :fullBookDTO.GetBookPages())
            	table.addRow(-1,bookPage.GetPageContent(),bookPage.GetPageNumber());
            // Prepare the stored procedure call
            String sql = "{call dbo.spAddNewBook(?, ?, ?, ?)}"; // Syntax for a stored procedure with a return value
            callableStatement = (SQLServerCallableStatement) 
            		connection.prepareCall("{call dbo.spAddNewBook(?, ?, ?, ?)}");
           
            // Set input parameters (index 2 and 3)
            callableStatement .setString(1, fullBookDTO.GetBook().GetBookTitle()); // Example: first input parameter
            callableStatement .setString(2, fullBookDTO.GetBook().GetBookAuthor()); // Example: second input parameter
            callableStatement .setStructured(3,"BookPagesTable",table);
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
	public boolean UpdateBook(BookDTO bookDTO) {
	    Connection connection = null;
        CallableStatement callableStatement = null;
        boolean RowsAffected = false; // Default value for return

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, ConnectionSettings.dbUserName, 
            		ConnectionSettings.dbPassword);

            // Prepare the stored procedure call
            String sql = "{call dbo.spUpdateBook(?, ?, ?, ?)}"; // Syntax for a stored procedure with a return value
            callableStatement = connection.prepareCall(sql);

            // Set input parameters (index 2 and 3)
            callableStatement.setInt(1,bookDTO.GetBookID());
            callableStatement.setString(2,bookDTO.GetBookTitle()); // Example: first input parameter
            callableStatement.setString(3,bookDTO.GetBookAuthor()); // Example: second input parameter
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
	public boolean DeleteBook(int BookID) {
	    Connection connection = null;
        CallableStatement callableStatement = null;
        boolean RowsAffected = false; // Default value for return

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, ConnectionSettings.dbUserName, 
            		ConnectionSettings.dbPassword);

            // Prepare the stored procedure call
            String sql = "{call dbo.spDeleteBook(?, ?)}"; // Syntax for a stored procedure with a return value
            callableStatement = connection.prepareCall(sql);

            // Set input parameters (index 2 and 3)
            callableStatement.setInt(1, BookID);
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
	public BookDTO GetBookByID(int BookID) {
		Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        BookDTO bookDTO=null;

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, 
            		ConnectionSettings.dbUserName, ConnectionSettings.dbPassword); 

            // Prepare the stored procedure call
            String sql = "{call dbo.spGetBookByID(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, BookID);
            // Execute the stored procedure
            callableStatement.execute();

            // Get the result set
            resultSet = callableStatement.getResultSet();

            // Process the result set
            while (resultSet.next()) {
            	bookDTO=new BookDTO(resultSet.getInt("BookID"),
            			            resultSet.getString("BookTitle"),
            			            resultSet.getString("BookAuthor"));
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

        return bookDTO;
	}

	@Override
	public List<BookDTO> GetAllBooks() {
		 Connection connection = null;
	        CallableStatement callableStatement = null;
	        ResultSet resultSet = null;
	        List<BookDTO> booksList = new ArrayList<>();

	        try {
	            // Establish connection to the database
	            connection = DriverManager.getConnection(ConnectionSettings.dbURL, 
	            		ConnectionSettings.dbUserName, ConnectionSettings.dbPassword); 

	            // Prepare the stored procedure call
	            String sql = "{call dbo.spGetAllBooks}";
	            callableStatement = connection.prepareCall(sql);

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
	public boolean DoesBookExist(int BookID) {
		Connection connection = null;
        CallableStatement callableStatement = null;
        boolean Found=false; // Default value for return

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, ConnectionSettings.dbUserName, 
            		ConnectionSettings.dbPassword);

            // Prepare the stored procedure call
            String sql = "{call dbo.spDoesBookExist(?, ?)}"; // Syntax for a stored procedure with a return value
            callableStatement = connection.prepareCall(sql);

            // Set input parameters (index 2 and 3)
            callableStatement.setInt(1, BookID);
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

	@Override
	public BookDTO GetBookByTitle(String Title) {
		Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        BookDTO bookDTO=null;

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, 
            		ConnectionSettings.dbUserName, ConnectionSettings.dbPassword); 

            // Prepare the stored procedure call
            String sql = "{call dbo.spGetBookByTitle(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, Title);
            // Execute the stored procedure
            callableStatement.execute();

            // Get the result set
            resultSet = callableStatement.getResultSet();

            // Process the result set
            while (resultSet.next()) {
            	bookDTO=new BookDTO(resultSet.getInt("BookID"),
            			            resultSet.getString("BookTitle"),
            			            resultSet.getString("BookAuthor"));
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

        return bookDTO;
	}

}

package Libraries;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Settings.ConnectionSettings;

public class clsLibraryData implements ILibraryData {

	@Override
	public int AddNewLibrary(LibraryDTO libraryDTO) {
		Connection connection = null;
        CallableStatement callableStatement = null;
        int result = -1; // Default value for return

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, ConnectionSettings.dbUserName, 
            		ConnectionSettings.dbPassword);

            // Prepare the stored procedure call
            String sql = "{call dbo.spAddNewLibrary(?, ?)}"; // Syntax for a stored procedure with a return value
            callableStatement = connection.prepareCall(sql);

            // Set input parameters (index 2 and 3)
            callableStatement.setInt(1,libraryDTO.GetUserID()); // Example: first input parameter

            // Register the return parameter (index 1)
            callableStatement.registerOutParameter(2, java.sql.Types.INTEGER);

            // Execute the stored procedure
            callableStatement.execute();

            // Retrieve the return value
            result = callableStatement.getInt(2);

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
	public boolean UpdateLibrary(LibraryDTO libraryDTO) {
		Connection connection = null;
        CallableStatement callableStatement = null;
        boolean RowsAffected = false; // Default value for return

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, ConnectionSettings.dbUserName, 
            		ConnectionSettings.dbPassword);

            // Prepare the stored procedure call
            String sql = "{call dbo.spUpdateLibrary(?, ?, ?)}"; // Syntax for a stored procedure with a return value
            callableStatement = connection.prepareCall(sql);

            // Set input parameters (index 2 and 3)
            callableStatement.setInt(1,libraryDTO.GetLibraryID());
            callableStatement.setInt(2,libraryDTO.GetUserID());
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
	public boolean DeleteLibrary(int LibraryID) {
		 Connection connection = null;
	        CallableStatement callableStatement = null;
	        boolean RowsAffected = false; // Default value for return

	        try {
	            // Establish connection to the database
	            connection = DriverManager.getConnection(ConnectionSettings.dbURL, ConnectionSettings.dbUserName, 
	            		ConnectionSettings.dbPassword);

	            // Prepare the stored procedure call
	            String sql = "{call dbo.spDeleteLibrary(?, ?)}"; // Syntax for a stored procedure with a return value
	            callableStatement = connection.prepareCall(sql);

	            // Set input parameters (index 2 and 3)
	            callableStatement.setInt(1, LibraryID);
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
	public boolean DoesLibraryExist(int LibraryID) {
		Connection connection = null;
        CallableStatement callableStatement = null;
        boolean Found=false; // Default value for return

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, ConnectionSettings.dbUserName, 
            		ConnectionSettings.dbPassword);

            // Prepare the stored procedure call
            String sql = "{call dbo.spDoesLibraryExist(?, ?)}"; // Syntax for a stored procedure with a return value
            callableStatement = connection.prepareCall(sql);

            // Set input parameters (index 2 and 3)
            callableStatement.setInt(1, LibraryID);
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
	public LibraryDTO GetLibraryByID(int LibraryID) {
		Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        LibraryDTO libraryDTO=null;

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, 
            		ConnectionSettings.dbUserName, ConnectionSettings.dbPassword); 

            // Prepare the stored procedure call
            String sql = "{call dbo.spGetLibraryByID(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, LibraryID);
            // Execute the stored procedure
            callableStatement.execute();

            // Get the result set
            resultSet = callableStatement.getResultSet();

            // Process the result set
            while (resultSet.next()) {
            	libraryDTO=new LibraryDTO(resultSet.getInt("LibraryID"),
            			                  resultSet.getInt("UserID"));
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

        return libraryDTO;
	}

	@Override
	public List<LibraryDTO> GetAllLibraries() {
	    Connection connection = null; 
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        List<LibraryDTO> LibrariesList = new ArrayList<>();

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, 
            		ConnectionSettings.dbUserName, ConnectionSettings.dbPassword); 

            // Prepare the stored procedure call
            String sql = "{call dbo.spGetAllLibraries";
            callableStatement = connection.prepareCall(sql);
            // Execute the stored procedure
            callableStatement.execute();

            // Get the result set
            resultSet = callableStatement.getResultSet();

            // Process the result set
            while (resultSet.next()) {
            	LibrariesList.add(new LibraryDTO(resultSet.getInt("LibraryID"),
		                                         resultSet.getInt("UserID"))); 
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

        return LibrariesList;
	}

	@Override
	public int GetUserLibraryID(int UserID) {
		Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        int LibraryID=-1;

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, 
            		ConnectionSettings.dbUserName, ConnectionSettings.dbPassword); 

            // Prepare the stored procedure call
            String sql = "{call dbo.spGetUserLibraryID(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, UserID);
            // Execute the stored procedure
            callableStatement.execute();

            // Get the result set
            resultSet = callableStatement.getResultSet();

            // Process the result set
            while (resultSet.next()) {
            	LibraryID=resultSet.getInt("LibraryID");
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

        return LibraryID;
	}
	
}

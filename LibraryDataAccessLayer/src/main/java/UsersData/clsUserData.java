package UsersData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Settings.ConnectionSettings;

public class clsUserData implements IUserData {

	@Override
	public int AddNewUser(UserDTO userDTO) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        int result = -1; // Default value for return

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, ConnectionSettings.dbUserName, 
            		ConnectionSettings.dbPassword);

            // Prepare the stored procedure call
            String sql = "{call dbo.spAddNewUser(?, ?, ?, ?)}"; // Syntax for a stored procedure with a return value
            callableStatement = connection.prepareCall(sql);

            // Set input parameters (index 2 and 3)
            callableStatement.setString(1, userDTO.GetUserName()); // Example: first input parameter
            callableStatement.setString(2, userDTO.GetEmail()); // Example: second input parameter
            callableStatement.setString(3, userDTO.GetPassword()); // Example: second input parameter
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
	public UserDTO GetUserInfo(int UserID) {
		 Connection connection = null;
	        CallableStatement callableStatement = null;
	        ResultSet resultSet = null;
	        UserDTO userDTO=null;

	        try {
	            // Establish connection to the database
	            connection = DriverManager.getConnection(ConnectionSettings.dbURL, 
	            		ConnectionSettings.dbUserName, ConnectionSettings.dbPassword); 

	            // Prepare the stored procedure call
	            String sql = "{call dbo.spGetUserByID(?)}";
	            callableStatement = connection.prepareCall(sql);
	            callableStatement.setInt(1, UserID);
	            // Execute the stored procedure
	            callableStatement.execute();

	            // Get the result set
	            resultSet = callableStatement.getResultSet();

	            // Process the result set
	            while (resultSet.next()) {
	            	userDTO=new UserDTO(resultSet.getInt("UserID"),
	            			                  resultSet.getString("UserName"),
	            			                  resultSet.getString("Email"),
	            			                  resultSet.getString("Password"));
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

	        return userDTO;
	}

	@Override
	public List<UserDTO> GetAllUsers() {
		 Connection connection = null;
	        CallableStatement callableStatement = null;
	        ResultSet resultSet = null;
	        List<UserDTO> usersList = new ArrayList<>();

	        try {
	            // Establish connection to the database
	            connection = DriverManager.getConnection(ConnectionSettings.dbURL, 
	            		ConnectionSettings.dbUserName, ConnectionSettings.dbPassword); 

	            // Prepare the stored procedure call
	            String sql = "{call dbo.spGetAllUsers}";
	            callableStatement = connection.prepareCall(sql);

	            // Execute the stored procedure
	            callableStatement.execute();

	            // Get the result set
	            resultSet = callableStatement.getResultSet();

	            // Process the result set
	            while (resultSet.next()) {
	            	usersList.add(new UserDTO(resultSet.getInt("UserID"),
	            			                  resultSet.getString("UserName"),
	            			                  resultSet.getString("Email"),
	            			                  resultSet.getString("Password")));
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

	        return usersList;
	}

	@Override
	public boolean DeleteUser(int UserID) {
	    Connection connection = null;
        CallableStatement callableStatement = null;
        boolean RowsAffected = false; // Default value for return

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, ConnectionSettings.dbUserName, 
            		ConnectionSettings.dbPassword);

            // Prepare the stored procedure call
            String sql = "{call dbo.spDeleteUser(?, ?)}"; // Syntax for a stored procedure with a return value
            callableStatement = connection.prepareCall(sql);

            // Set input parameters (index 2 and 3)
            callableStatement.setInt(1, UserID);
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
	public boolean DoesUserExist(int UserID) {
		Connection connection = null;
        CallableStatement callableStatement = null;
        boolean Found=false; // Default value for return

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(ConnectionSettings.dbURL, ConnectionSettings.dbUserName, 
            		ConnectionSettings.dbPassword);

            // Prepare the stored procedure call
            String sql = "{call dbo.spDoesUserExist(?, ?)}"; // Syntax for a stored procedure with a return value
            callableStatement = connection.prepareCall(sql);

            // Set input parameters (index 2 and 3)
            callableStatement.setInt(1, UserID);
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
	public boolean UpdateUser(UserDTO userDTO) {
		    Connection connection = null;
	        CallableStatement callableStatement = null;
	        boolean RowsAffected = false; // Default value for return

	        try {
	            // Establish connection to the database
	            connection = DriverManager.getConnection(ConnectionSettings.dbURL, ConnectionSettings.dbUserName, 
	            		ConnectionSettings.dbPassword);

	            // Prepare the stored procedure call
	            String sql = "{call dbo.spUpdateUser(?, ?, ?, ?, ?)}"; // Syntax for a stored procedure with a return value
	            callableStatement = connection.prepareCall(sql);

	            // Set input parameters (index 2 and 3)
	            callableStatement.setInt(1, userDTO.GetUserID());
	            callableStatement.setString(2, userDTO.GetUserName()); // Example: first input parameter
	            callableStatement.setString(3, userDTO.GetEmail()); // Example: second input parameter
	            callableStatement.setString(4, userDTO.GetPassword()); // Example: second input parameter
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
	public UserDTO GetUserInfo(String Email, String Password) {
		 Connection connection = null;
	        CallableStatement callableStatement = null;
	        ResultSet resultSet = null;
	        UserDTO userDTO=null;

	        try {
	            // Establish connection to the database
	            connection = DriverManager.getConnection(ConnectionSettings.dbURL, 
	            		ConnectionSettings.dbUserName, ConnectionSettings.dbPassword); 

	            // Prepare the stored procedure call
	            String sql = "{call dbo.spGetUserByEmailAndPassword(?,?)}";
	            callableStatement = connection.prepareCall(sql);
	            callableStatement.setString(1, Email);
	            callableStatement.setString(2, Password);
	            // Execute the stored procedure
	            callableStatement.execute();

	            // Get the result set
	            resultSet = callableStatement.getResultSet();

	            // Process the result set
	            while (resultSet.next()) {
	            	userDTO=new UserDTO(resultSet.getInt("UserID"),
	            			                  resultSet.getString("UserName"),
	            			                  resultSet.getString("Email"),
	            			                  resultSet.getString("Password"));
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

	        return userDTO;
	}
}

package controller;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcDriver {
	private static final String SERVER = "localhost";
	private static final int PORT = 1521;
	private static final String SERVICE_NAME = "xe";
	private String username;
	private String password;

	private Connection connection = null;

	/**
	 * Initializes the newly created JdbcDriver.
	 */
	public JdbcDriver() {
	}

	/**
	 * Connects to the database.
	 * @throws SQLException
	 */
	public void connect() throws SQLException
	{
		String dataSourceName = this.getDataSourceName();
		try {
			connection = DriverManager.getConnection(dataSourceName, username, password);
		} catch (SQLException e) {
			Logger.getLogger(JdbcDriver.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	/**
	 * Gets the data source name to connect to the database.
	 * @return DSN
	 */
	private String getDataSourceName() {
		// Data source name is formatted as follows (for Oracle SQL): jdbc:oracle:thin:@localhost:1521/XE
		String dataSourceName = String.format("jdbc:oracle:thin:@%s:%d/%s", SERVER, PORT, SERVICE_NAME);

		return dataSourceName;
	}

//	/**
//	 * Performs a retrieval from the database (ie: SELECT)
//	 * @param query Query to send to database.
//	 * @return Returns the results as a ResultSet
//	 * @throws SQLException Thrown if problem performing query.
//	 */
//	public ResultSet get(String query) throws SQLException
//	{
//		Statement statement = connection.createStatement();
//		ResultSet results = statement.executeQuery(query);
//
//		return results;
//	}
//
//	/**
//	 * Performs an update query (UPDATE, DELETE, DROP, etc.) on the database.
//	 * @param query Query to send to database.
//	 * @return Number of rows modified.
//	 * @throws SQLException
//	 */
//	public int update(String query) throws SQLException
//	{
//		Statement statement = connection.createStatement();
//		int ret = statement.executeUpdate(query);
//
//		return ret;
//	}

	/**
	 * Disconnects from the database.
	 * @throws SQLException
	 */
	public void disconnect() throws SQLException
	{
		if (connection != null && !connection.isClosed())
			connection.close();
	}
}

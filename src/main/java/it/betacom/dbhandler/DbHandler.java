package it.betacom.dbhandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHandler {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/dbWebApp";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "rootroot";
    private static Connection connection;
    
    private DbHandler() {
    	
    }
    
    
    public static Connection getConnection() throws SQLException {
		if(connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
		}
		return connection;
	}
	
	public static void closeConnection() throws SQLException {
		if(connection != null && !connection.isClosed()) {
			connection.close();
		}
	}
}

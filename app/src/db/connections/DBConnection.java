package db.connections;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	private static Connection connection	= null;
	
	// making the default constructor private so this class can not be instantiated
	private DBConnection() {}
	
	public static Connection getConnection() {
		try {
			if( connection == null || connection.isClosed()) {
				//retrieving informations to connect to db
				Properties appProperties = new Properties();
				InputStream is = DBConnection.class.getClassLoader().getResourceAsStream("app.properties");
				appProperties.load(is);
				String rDBDriver 	= appProperties.getProperty("rdbdriver");
				String rDBUrl		= appProperties.getProperty("rdburl");
				String rDBUser		= appProperties.getProperty("rdbuser");
				String rDBPassword	= appProperties.getProperty("rdbpassword");
				
				//opening a connection
				Class.forName(rDBDriver);
				connection = DriverManager.getConnection(rDBUrl, rDBUser, rDBPassword);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found - check if jdbc driver missing or if its name is misspelled");
		} catch (SQLException e) {
			System.out.println("Cannot open connection - wrong credentials ?");
		} catch (IOException e) {
			System.out.println("Config file cannot be found or accessed");
		}
		return connection;
	}
}

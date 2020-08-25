package com.revature.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	public static Connection getConnection() throws SQLException {
		
		//For compatibility with other technologies/frameworks will need to register our Driver
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	
	String url = "jdbc:postgresql://javafs200803.cpgbaphxuyjb.us-east-2.rds.amazonaws.com:5432/project0";
	String username = "postgres";
	String password = "masterpassword";
	
	return DriverManager.getConnection(url, username, password);
}
}

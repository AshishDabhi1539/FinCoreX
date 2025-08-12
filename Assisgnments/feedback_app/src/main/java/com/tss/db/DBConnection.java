package com.tss.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	private static Connection connection;

	public static Connection connect() {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        return DriverManager.getConnection("jdbc:mysql://localhost:3306/feedback", "root", "Root@123");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

}

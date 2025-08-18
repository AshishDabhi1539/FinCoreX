package com.tss.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection connection;

	public static Connection connect() {
		if (connection == null) {
			try {

				Class.forName("com.mysql.cj.jdbc.Driver");

				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/organization", "root", "Root@123");
				System.out.println("Connection Successful");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return connection;
	}
}

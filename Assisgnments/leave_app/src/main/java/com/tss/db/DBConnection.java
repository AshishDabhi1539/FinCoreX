package com.tss.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/leave_application	";
    private static final String USER = "root"; // change as needed
    private static final String PASSWORD = "Root@123"; // change as needed

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
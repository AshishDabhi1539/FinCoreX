package com.banking.test;

import java.sql.Connection;
import java.sql.SQLException;
import com.banking.db.DBConnection;

public class DBConnectionTest {
    
    public static void main(String[] args) {
        System.out.println("Testing database connection...");
        
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("✅ Database connection successful!");
            System.out.println("Database URL: " + conn.getMetaData().getURL());
            System.out.println("Database Product: " + conn.getMetaData().getDatabaseProductName());
            System.out.println("Database Version: " + conn.getMetaData().getDatabaseProductVersion());
            conn.close();
            System.out.println("✅ Connection closed successfully!");
            
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed!");
            System.err.println("Error: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            
            // Common troubleshooting tips
            System.err.println("\n🔧 Troubleshooting tips:");
            System.err.println("1. Make sure MySQL server is running");
            System.err.println("2. Verify database 'banking' exists");
            System.err.println("3. Check username/password in DBConnection.java");
            System.err.println("4. Ensure MySQL Connector/J is in classpath");
        }
    }
}

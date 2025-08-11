package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tss.model.DBModel;

public class UserDao {

	public void addNewUser(Connection connection, DBModel dbmodel) {
		
		try {
			PreparedStatement stmt = connection
					.prepareStatement("insert into registration (username, password) values (?,?)");

			stmt.setString(1, dbmodel.getUsername());
			stmt.setString(2, dbmodel.getPassword());
			int rowsInserted = stmt.executeUpdate();

			if (rowsInserted > 0) {
                System.out.println("New user registered successfully.");
            } else {
                System.out.println("Failed to register user.");
            }
			
			
			
	        
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
		
		public List<DBModel> getAllUsers(Connection connection) {
		    List<DBModel> userList = new ArrayList<>();

		    try {
		        PreparedStatement stmt = connection.prepareStatement("SELECT username, password FROM registration");
		        ResultSet rs = stmt.executeQuery();

		        while (rs.next()) {
		            String username = rs.getString("username");
		            String password = rs.getString("password");

		            DBModel user = new DBModel(username, password);
		            userList.add(user);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return userList;
		}

	
}

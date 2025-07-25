package com.tss.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tss.model.Student;

public class Database {

	Connection connection = null;

	public Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tss_students_db", "root", "Root@123");
			System.out.println("Database connected");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void readAllStatement() {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM students");

			while (rs.next()) {
				System.out.print(rs.getInt("student_id") + "\t" + rs.getInt("roll_number") + "\t" + rs.getString("name")
						+ "\t" + rs.getInt("age") + "\t" + rs.getFloat("percentage") + "\n");
			}
			
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addStudent(Student student) {
	    try {
	        Statement statement = connection.createStatement();

	        String sql = "INSERT INTO students VALUES (" +
	                     student.getStudentId() + ", " +
	                     student.getRollNumber() + ", '" +
	                     student.getName() + "', " +
	                     student.getAge() + ", " +
	                     student.getPercentage() + ")";

	        int updates = statement.executeUpdate(sql);

	        if (updates > 0) {
	            System.out.println("Student Record Added Successfully");
	        } else {
	            System.out.println("Student Record Not Added");
	        }

	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


		
	
}

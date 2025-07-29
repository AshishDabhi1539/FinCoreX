package com.tss.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tss.database.DbConnection;
import com.tss.model.Student;

public class StudentDao {

    private Connection connection;

    public StudentDao() {
        this.connection = DbConnection.connection();
    }

    
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                Student student = new Student();
                student.setStudentId(result.getInt("student_id"));
                student.setName(result.getString("name"));
                student.setAge(result.getInt("age"));
                student.setPercentage(result.getDouble("percentage"));
                student.setRollNumber(result.getInt("roll_number"));
                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

   
    public boolean insertStudent(Student student) {
        String sql = "INSERT INTO students (student_id, name, age, percentage, roll_number) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, student.getStudentId());      
            pstmt.setString(2, student.getName());        
            pstmt.setInt(3, student.getAge());            
            pstmt.setDouble(4, student.getPercentage());  
            pstmt.setInt(5, student.getRollNumber());     

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    
    public Student getStudentById(int id) {
        String sql_query = "SELECT * FROM students WHERE student_id = ?";
        Student student = null;

        try (PreparedStatement pstmt = connection.prepareStatement(sql_query)) {
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                student = new Student();
                student.setStudentId(result.getInt("student_id"));
                student.setName(result.getString("name"));
                student.setAge(result.getInt("age"));
                student.setPercentage(result.getDouble("percentage"));
                student.setRollNumber(result.getInt("roll_number"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

  
    public boolean updatePercentageById(int id, double newPercentage) {
        String sql_query = "UPDATE students SET percentage = ? WHERE student_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql_query)) {
            pstmt.setDouble(1, newPercentage);
            pstmt.setInt(2, id);

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    
    public boolean deleteStudent(int id) {
        String sql_query = "DELETE FROM students WHERE student_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql_query)) {
            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}

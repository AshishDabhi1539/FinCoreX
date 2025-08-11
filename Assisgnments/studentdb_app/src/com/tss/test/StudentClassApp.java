package com.tss.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.tss.database.DbConnection;
import com.tss.util.MetaData;

public class StudentClassApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        StudentController controller = new StudentController();
//
//        while (true) {
//            
//            System.out.println("1. Read All Students");
//            System.out.println("2. Add New Student");
//            System.out.println("3. Read a Student By ID");
//            System.out.println("4. Update Student's Percentage By ID");
//            System.out.println("5. Delete a Student By ID");
//            System.out.println("6. Exit");
//            System.out.print("Enter your choice: ");
//
//            int choice = scanner.nextInt();
//
//            switch (choice) {
//                case 1:
//                    controller.readAllRecords();
//                    break;
//                case 2:
//                    controller.addStudent(scanner);
//                    break;
//                case 3:
//                    controller.getStudentById(scanner);
//                    break;
//                case 4:
//                    controller.updatePercentageById(scanner);
//                    break;
//                case 5:
//                    controller.deleteStudentById(scanner);
//                    break;
//                case 6:
//                    System.out.println("Exiting...");
//                    scanner.close();
//                    return;  
//                default:
//                    System.out.println("Invalid choice! Please try again.");
//            }
//        }
        
        Connection connection = DbConnection.connection();

        MetaData.printDatabaseMetadata(connection);
        MetaData.printTableMetadata(connection, "students");
        

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

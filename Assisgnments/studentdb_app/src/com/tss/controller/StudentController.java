package com.tss.controller;

import java.util.Scanner;

import com.tss.model.Student;
import com.tss.service.StudentService;

public class StudentController {

    private StudentService service;

    public StudentController() {
        service = new StudentService();
    }

    
    public void readAllRecords() {
        var students = service.readAllStudent();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            
            System.out.println("----------------------------------------------------------------------------------");
            System.out.printf("| %-10s | %-20s | %-5s | %-10s | %-10s |%n", 
                              "Student ID", "Name", "Age", "Percentage", "Roll No");
            System.out.println("----------------------------------------------------------------------------------");

            
            for (Student student : students) {
                System.out.printf("| %-10d | %-20s | %-5d | %-10.2f | %-10d |%n",
                        student.getStudentId(),
                        student.getName(),
                        student.getAge(),
                        student.getPercentage(),
                        student.getRollNumber());
            }

            System.out.println("----------------------------------------------------------------------------------");
        }
    }


   
    public void addStudent(Scanner scanner) {
        try {
            System.out.print("Enter the student ID: ");
            int id = scanner.nextInt();

            System.out.print("Enter the roll number: ");
            int roll = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            if (name == null) {
                System.out.println("Name cannot be blank.");
                return;
            }

            System.out.print("Enter age: ");
            int age = scanner.nextInt();
            if (age <18) {
                System.out.println("Age must be greater than 18.");
                return;
            }

            System.out.print("Enter percentage: ");
            double percentage = scanner.nextDouble();
            if (percentage < 0 || percentage > 100) {
                System.out.println("Percentage must be between 0 and 100.");
                return;
            }

            Student student = new Student(id,  age, name,  roll, percentage);
            boolean success = service.addStudent(student);
            System.out.println(success ? "Student added successfully." : "Failed to add student.");

        } catch (Exception e) {
            System.out.println("Invalid input. Please enter correct values.");
            scanner.nextLine();	
        }
    }

    
    public void getStudentById(Scanner scanner) {
        try {
            System.out.print("Enter student ID: ");
            int id = scanner.nextInt();
            if (id <= 0) {
                System.out.println("ID must be positive.");
                return;
            }

            Student student = service.getStudentById(id);
            if (student != null) {
                System.out.printf("Student ID: %d | Name: %s | Age: %d | Percentage: %.2f | Roll No: %d%n",
                        student.getStudentId(), student.getName(), student.getAge(),
                        student.getPercentage(), student.getRollNumber());
            } else {
                System.out.println("Student not found.");
            }

        } catch (Exception e) {
            System.out.println("Invalid input.");
            scanner.nextLine(); 
        }
    }

   
    public void updatePercentageById(Scanner scanner) {
        try {
            System.out.print("Enter student ID: ");
            int id = scanner.nextInt();
            if (id <= 0) {
                System.out.println("ID must be positive.");
                return;
            }

            System.out.print("Enter new percentage: ");
            double percentage = scanner.nextDouble();
            if (percentage < 0 || percentage > 100) {
                System.out.println("Percentage must be between 0 and 100.");
                return;
            }

            boolean updated = service.updateStudentPercentage(id, percentage);
            System.out.println(updated ? "Percentage updated successfully." : "Student not found or update failed.");

        } catch (Exception e) {
            System.out.println("Invalid input.");
            scanner.nextLine();
        }
    }

    
    public void deleteStudentById(Scanner scanner) {
        try {
            System.out.print("Enter student ID to delete: ");
            int id = scanner.nextInt();
            if (id <= 0) {
                System.out.println("ID must be positive.");
                return;
            }

            boolean deleted = service.deleteStudentById(id);
            System.out.println(deleted ? "Student deleted successfully." : "Student not found.");

        } catch (Exception e) {
            System.out.println("Invalid input.");
            scanner.nextLine(); 
        }
    }
}

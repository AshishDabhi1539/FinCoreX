package com.tss.test;

import java.util.Scanner;

import com.tss.database.Database;
import com.tss.model.Student;

public class DbTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

       
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();

        System.out.print("Enter Roll Number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Age: ");
        int age = scanner.nextInt();

        System.out.print("Enter Percentage: ");
        double percentage = scanner.nextDouble();

       
        Student student = new Student(studentId, rollNumber, name, age, percentage);

       
        Database database = new Database();
        database.addStudent(student);
        database.readAllStatement();

       
        scanner.close();
    }
}

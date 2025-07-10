package com.tss.test;

import java.util.Scanner;

import com.tss.model.Student;

public class StudentTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the details of first student: ");
		System.out.println("Enter the details of second student: ");
		
		
		Student student1 = new Student();
		studentDisplay(student1,scanner);
		
		Student student2 = new Student();
		studentDisplay(student2,scanner);
		
		

	}
	
	public static void studentDisplay(Student student, Scanner scanner) {
		System.out.println("Enter Roll Number of Student:");
		int rollnumber = scanner.nextInt();
		
		
		
		
	}

}

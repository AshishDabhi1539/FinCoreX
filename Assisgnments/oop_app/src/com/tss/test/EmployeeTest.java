package com.tss.test;

import java.time.LocalDate;
import java.util.Scanner;

import com.tss.model.Employee;

public class EmployeeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		Employee employee1 = new Employee();
		LocalDate joiningDate = LocalDate.now();
		displayEmployeeDetails(employee1, scanner, joiningDate);
	}

	private static void displayEmployeeDetails(Employee employee1, Scanner scanner, LocalDate joiningDate) {
		// TODO Auto-generated method stub
		System.out.println("Enter the employee ID: ");
		int employeeId = scanner.nextInt();
		employee1.setEmplyeeId(employeeId);
		
		scanner.nextLine();
		System.out.println("Enter the employee name: ");
		String employeeName = scanner.nextLine();
		employee1.setEmployeeName(employeeName);
		
		System.out.println("Enter the employee joining date(YYYY-MM-DD): ");
		String employeeJoiningDate = scanner.nextLine();
		
		
		LocalDate employeeJoiningDate1 = null;
				
		employeeJoiningDate1 = LocalDate.parse(employeeJoiningDate);
		
		employee1.setEmployeeJoiningDate(employeeJoiningDate1);
		
		System.out.println("Enter the employee salary: ");
		int employeeSalary = scanner.nextInt();
		employee1.setEmployeeSalary(employeeSalary);
		
		employee1.display();
	}

}

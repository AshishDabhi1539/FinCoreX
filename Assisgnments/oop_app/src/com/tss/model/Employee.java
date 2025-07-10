package com.tss.model;

import java.time.LocalDate;
import java.time.Period;
public class Employee {

	private int employeeId;
	private String employeeName;
	private LocalDate employeeJoiningDate;
	private int employeeSalary;
	LocalDate currentDate = LocalDate.now();
	
	
	public void setEmplyeeId(int employeeId) {
		this.employeeId=employeeId;
	}
	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public void setEmployeeJoiningDate(LocalDate employeeJoiningDate) {
		this.employeeJoiningDate = employeeJoiningDate;
	}
	
	public void setEmployeeSalary(int employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	
	public LocalDate getEmployeeJOiningDate() {
		return employeeJoiningDate;
	}
	
	public int getEmployeeSalary() {
		return employeeSalary;
	}
	
	public void display() {
		System.out.println("Employee ID: "+employeeId);
		System.out.println("Employee Name: "+employeeName);
		System.out.println("Employee Joining Date(YYYY-MM-DD): "+employeeJoiningDate);
		
		employeeExperienceCalculate(employeeJoiningDate);
		
		System.out.println("Employee Salary: " + employeeSalary);
		
		employeeBonusCalcualte(employeeSalary);
	}

	private void employeeBonusCalcualte(int employeeSalary) {
		// TODO Auto-generated method stub
		double employeeBonus = employeeSalary * 0.5;
		
		System.out.println("Bonus of the employee is: "+employeeBonus);
		
	}

	private void employeeExperienceCalculate(LocalDate employeeJoiningDate) {
		// TODO Auto-generated method stub
		Period experience = Period.between(employeeJoiningDate, currentDate);
		
		System.out.println("Experience of the employee is: "+experience.getYears());
		
	}
}

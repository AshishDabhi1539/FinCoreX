package com.tss.model;

public class Employee {

	int id;
	String name;
	double salary;
	String dept;

	public Employee(int id, String name, double salary, String dept) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.dept = dept;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public String getDept() {
		return dept;
	}

	public String toString() {
		return name + " (" + dept + ", " + salary + ")";
	}

}

package com.tss.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tss.model.Employee;

public class EmployeeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Employee> employees = Arrays.asList(new Employee(1, "Mahek", 70000, "Intern"),
				new Employee(2, "Harshad", 90000, "IT"), new Employee(3, "Dharmi", 90000, "IT"),
				new Employee(4, "Nikul", 80000, "HR"), new Employee(5, "Harshil", 85000, "Testing"));

	
		System.out.println("Sorted Employees:");
		employees.stream()
				.sorted(Comparator.comparingDouble(Employee::getSalary).reversed().thenComparing(Employee::getName))
				.forEach(System.out::println);	

		
		System.out.println("\nHighest Paid by Department:");
		Map<String, Employee> topInDept = employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors
				.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)), Optional::get)));

		topInDept.forEach((dept, emp) -> System.out.println(dept + ": " + emp));
	}

}

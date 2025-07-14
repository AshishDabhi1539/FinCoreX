package com.tss.behavioral.strategy.test;

import com.tss.behavioral.strategy.model.Consultant;
import com.tss.behavioral.strategy.model.Employee;
import com.tss.behavioral.strategy.model.SeniorConsultant;
import com.tss.behavioral.strategy.model.TechLead;

public class EmployeeTest {

	public static void main(String[] args) {
		Employee employee = new Employee(1, "Mahek Morzaria", new Consultant());
		System.out.println(employee.getDescription());
		System.out.println("Responsibility: " + employee.getResponsibility());

		employee.promote(new SeniorConsultant());
		System.out.println("\nAfter Promotion:");
		System.out.println(employee.getDescription());
		System.out.println("Responsibility: " + employee.getResponsibility());

		employee.promote(new TechLead());
		System.out.println("\nAfter Second Promotion:");
		System.out.println(employee.getDescription());
		System.out.println("Responsibility: " + employee.getResponsibility());
	}
}

package com.tss.model;

public class Student {

	private StudentService service;
	
	public Student(StudentService service) {
		super();
		this.service = service;
	}

	int calculateAverage() {
		return service.getFinalMarks()/service.getNumberOfStudents();
	}
}

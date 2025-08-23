package com.tss.hibernate.service;

import java.util.List;

import com.tss.hibernate.entity.Student;

public interface StudentService {

	Student addNewStudent(Student student);
	
	List <Student> getAllStudents();
	
	Student readStudentById(int id);
	
	Student readStudentByRollNumber(int rollNumber);

	List<Student> readStudentByName(String name);

}

package com.tss.hibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.hibernate.entity.Student;
import com.tss.hibernate.service.StudentService;

@RestController
@RequestMapping("/studentapp")
public class StudentController {

	@Autowired
	private StudentService service;
	@PostMapping("/students")
	public Student addNewStudent(@RequestBody Student student) {
		return service.addNewStudent(student);
	}
	
	@GetMapping("/student")
	public List<Student> getAllStudent(){
		return service.getAllStudents();
	}
	
	@GetMapping("/student/{studentId}")
	public Student findStudentId(@PathVariable int studentId){
		return service.readStudentById(studentId);
	}
	
	@GetMapping("/student/roll/{rollNumber}")
	public Student findStudentByRollNumber(@PathVariable int rollNumber) {
	    return service.readStudentByRollNumber(rollNumber);
	}

	@GetMapping("/student/name/{name}")
	public List<Student> findStudentByName(@PathVariable String name) {
	    return service.readStudentByName(name);
	}

}

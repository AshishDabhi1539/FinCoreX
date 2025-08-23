package com.tss.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.hibernate.dao.StudentDao;
import com.tss.hibernate.entity.Student;
@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentDao service;

	@Override
	public Student addNewStudent(Student student) {
		// TODO Auto-generated method stub
		return service.addNewStudent(student);
	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return service.getAllStudents();
	}

	@Override
	public Student readStudentById(int id) {
		// TODO Auto-generated method stub
		return service.readStudentById(id);
	}
	
	@Override
	public Student readStudentByRollNumber(int rollNumber) {
	    return service.readStudentByRollNumber(rollNumber);
	}

	@Override
	public List<Student> readStudentByName(String name) {
	    return service.readStudentByName(name);
	}


}

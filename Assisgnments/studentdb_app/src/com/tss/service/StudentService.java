package com.tss.service;

import java.util.List;

import com.tss.dao.StudentDao;
import com.tss.model.Student;

public class StudentService {

	private StudentDao studentDao;

	public StudentService() {
		super();
		this.studentDao = new StudentDao();
	}
	
	public List<Student> readAllStudent()
	{
		return studentDao.getAllStudents();
	}
	
	public boolean addStudent(Student student) {
		return studentDao.insertStudent(student);
	}
	
	public Student getStudentById(int id) {
		return studentDao.getStudentById(id);
	}

	
	public boolean updateStudentPercentage(int id, double newPercentage) {
		return studentDao.updatePercentageById(id, newPercentage);
	}

	
	public boolean deleteStudentById(int id) {
		return studentDao.deleteStudent(id);
	}
}

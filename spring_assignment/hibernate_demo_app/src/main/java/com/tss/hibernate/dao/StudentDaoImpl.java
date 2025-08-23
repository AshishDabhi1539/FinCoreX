package com.tss.hibernate.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tss.hibernate.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentDaoImpl implements StudentDao{

	@Autowired
	public EntityManager manager;
	
	
	@Override
	@Transactional
	public Student addNewStudent(Student student) {
		// TODO Auto-generated method stub
		return manager.merge(student);
	}


	@Override
	public List<Student> getAllStudents() {
		//JPQL
		TypedQuery<Student> query= manager.createQuery("SELECT s FROM Student s", Student.class);
		return query.getResultList();
	}


	@Override
	public Student readStudentById(int id) {
		// TODO Auto-generated method stub
		return manager.find(Student.class, id);
	}
	
	@Override
	public Student readStudentByRollNumber(int rollNumber) {
	    TypedQuery<Student> query = manager.createQuery("SELECT s FROM Student s WHERE s.rollNumber = :rollNumber", Student.class);
	    query.setParameter("rollNumber", rollNumber);
	    return query.getSingleResult();
	}

	@Override
	public List<Student> readStudentByName(String name) {
	    TypedQuery<Student> query = manager.createQuery("SELECT s FROM Student s WHERE s.firstName LIKE :name OR s.lastName LIKE :name", Student.class);
	    query.setParameter("name", "%" + name + "%");
	    return query.getResultList();
	}


}

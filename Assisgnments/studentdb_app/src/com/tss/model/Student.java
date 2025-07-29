package com.tss.model;

public class Student {

	private int studentId;
	private int rollNumber;
	private String name;
	private int age;
	private double percentage;
	
	public Student() {
		
	}
	
	public Student(int studentId, int rollNumber, String name, int age, double percentage) {
        this.studentId = studentId;
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.percentage = percentage;
    }

    // Getters
    public int getStudentId() {
        return studentId;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getPercentage() {
        return percentage;
    }

    // Setters
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

}

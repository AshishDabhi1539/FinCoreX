package com.tss.model;

public class Student {

	private int rollNumber;
	private String name;
	private int age;
	private int sub1_marks;
	private int sub2_marks;
	private int sub3_marks;
	
	
	
	
	public void setAttributes(int sub1_marks, int sub2_marks, int sub3_marks, int age, String name, int rollNumber) {
		this.age = age;
		this.name = name;
		this.rollNumber = rollNumber;
		this.sub1_marks = sub1_marks;
		this.sub2_marks = sub2_marks;
		this.sub3_marks = sub3_marks;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public int getRollNumber() {
		return rollNumber;
	}
	
	public int getSub1Marks() {
		return sub1_marks;
	}
	
	public int getSub2Marks() {
		return sub2_marks;
	}
	
	public int getSub3Marks() {
		return sub3_marks;
	}
	
	
	public void display() {
		System.out.print("Name: "+name);
		System.out.print("Roll Number: "+rollNumber);
		System.out.println("Age: "+ age);
		System.out.println("Subject 1 marks: "+sub1_marks);
		System.out.println("Subject 2 marks: "+sub2_marks);
		System.out.println("Subject 2 marks: "+sub2_marks);
		
		averageOfSubjects();
		
	}
	public void averageOfSubjects() {
		
		int average = (sub1_marks + sub2_marks + sub3_marks)/3;
		
		System.out.println("Average of the three subjects is: "+ average);
	}
}

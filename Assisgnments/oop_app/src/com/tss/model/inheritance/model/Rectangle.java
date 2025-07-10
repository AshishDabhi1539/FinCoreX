package com.tss.model.inheritance.model;

public class Rectangle extends Shape {

	private int lenght;
	private int breadth;
	
	public Rectangle(int lenght, int breadth) {
		// TODO Auto-generated constructor stub
		this.lenght = lenght;
		this.breadth = breadth;
	}

	public void area() {
		// TODO Auto-generated method stub
		double area = lenght * breadth;
		System.out.println("Area of the rectangle is: "+area);
	}

	
}

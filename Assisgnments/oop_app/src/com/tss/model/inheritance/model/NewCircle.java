package com.tss.model.inheritance.model;

public class NewCircle implements IShape{

	double radius;
	public NewCircle(double radius) {
		// TODO Auto-generated constructor stub
		this.radius = radius;
	}
	@Override
	public void area() {
		// TODO Auto-generated method stub
		double area = 3.14 * radius * radius;
		System.out.println("Area of circle is: "+area);
	}

}

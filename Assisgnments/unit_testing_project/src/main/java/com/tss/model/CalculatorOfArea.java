package com.tss.model;

public class CalculatorOfArea {

	double areaOfCircle(double radius) {
		double area = radius * 3.14 * radius;
		
		return area;
	}
	
	double areaOfRectangle(double lenght, double breadth) {
		double area = lenght*breadth;
		
		return area;
	}
}

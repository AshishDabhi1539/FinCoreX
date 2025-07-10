package com.tss.model.inheritance.test;

import com.tss.model.inheritance.model.Circle;
import com.tss.model.inheritance.model.Rectangle;
import com.tss.model.inheritance.model.Shape;

public class ShapeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Shape shape;
		
		Shape circle = new Circle(10);
		circle.area();
		
		
		Shape rectangle = new Rectangle(10, 20);
		rectangle.area();

	}

}

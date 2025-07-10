package com.tss.model.inheritance.test;

import com.tss.model.inheritance.model.NewCircle;
import com.tss.model.inheritance.model.NewRectangle;

public class NewShapeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		NewCircle circle = new NewCircle(20);
		circle.area();
		
		
		NewRectangle rectangle = new NewRectangle(20, 30);
		rectangle.area();
		
	}

}

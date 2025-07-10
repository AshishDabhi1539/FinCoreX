package com.tss.test;

import com.tss.model.Demo;

public class DemoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Demo demo1= new Demo();
		
		demo1.display();
		
		Demo demo2 = new Demo();
		
		demo2.display();
	}
	
	static {
		System.out.println("Inside main");
	}

	{
		System.out.println("Do not print");
	}
}

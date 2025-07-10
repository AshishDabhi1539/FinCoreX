package com.tss.test;

import com.tss.model.Demo;
import com.tss.model.IDemo;

public class DemoTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		IDemo demo = Demo :: display;
		show(demo);
		Demo d1 = new Demo();

		IDemo demo1 = d1 :: show  ;
		show(demo1);
		
	}
	private static void show(IDemo demo)
	{
		demo.accept();
	}
	

}

package com.tss.model.inheritance.test;

import com.tss.model.inheritance.model.Demo1;
import com.tss.model.inheritance.model.IDemo;

public class DemoTest {

	public static void main(String[] args) {
		
		Demo1 object = new Demo1();
		object.method1();
		
		IDemo.method2();

	}

}
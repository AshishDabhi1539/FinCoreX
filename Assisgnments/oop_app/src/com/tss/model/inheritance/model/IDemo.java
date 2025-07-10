package com.tss.model.inheritance.model;

public interface IDemo {
	
	default void method1()
	{
		method3();
		System.out.println("this is default method");
	}
	
	static void method2()
	{
		System.out.println("This is Static Method");
	}
	private void method3()
	{
		System.out.println("This is Private Method");
	}
}

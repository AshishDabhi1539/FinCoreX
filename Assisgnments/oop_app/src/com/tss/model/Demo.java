package com.tss.model;

public class Demo {

	private int a;
	private int b;
	private static int c;
	
	public Demo(){
		System.out.println("Inside constructor");
	}
	public void increament() {
		a++;
		b++;
		c++;
	}
	
	public void display() {
		System.out.println("a="+a);
		System.out.println("b="+b);
		System.out.println("c="+c);
	}
	
	public static void increament_1() {
		c++;
	}
	
	{
		System.out.println("Inside demo");
	}
	
	static {
		System.out.println("Inside static demo");
	}
}

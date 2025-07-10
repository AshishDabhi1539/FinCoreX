package com.tss.test;

public class WrapperTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Boolean b = new Boolean("true");
		Boolean b1 = new Boolean(true);
		
		System.out.println(b);
		System.out.println(b1);
		
		System.out.println(b.hashCode());
		System.out.println(b1.hashCode());
		
		System.out.println(b.equals(b1));
		System.out.println(b==b1);
		System.out.println(b);
		System.out.println(b1);
		
		System.out.println(b.hashCode());
		System.out.println(b1.hashCode());
	}

}

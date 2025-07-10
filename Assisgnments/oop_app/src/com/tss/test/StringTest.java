package com.tss.test;

public class StringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StringBuilder string1 = new StringBuilder("mahek");
		StringBuilder string2 = new StringBuilder("xyz");

		System.out.println(string1.hashCode());
		System.out.println(string2.hashCode());

		System.out.println(string1 == string2);

		string1 = string2;

		System.out.println(string1.hashCode());
		System.out.println(string2.hashCode());

		System.out.println(string1 == string2);
		
		System.out.println(string1);  
		System.out.println(string2);
	}

}

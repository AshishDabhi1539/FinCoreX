package com.tss.basics.commandline;

import java.util.Scanner;

public class SimpleCalculator {

	public static void main(String[] args) {
		
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		
//		int sum = a+b;
//		int diff = a-b;
//		int multiply = a*b;
//		int div = a/b;
//		
//		System.out.println("Sum = " + sum);
//		System.out.println("Difference = " + diff);
//		System.out.println("Multiplication = " + multiply);
//		System.out.println("Division = " + div);
		
		addition(a, b);
		difference(a, b);
		multiplication(a, b);
		division(a, b);

	}

	private static void addition (int num1 , int num2) {
		int sum = num1 + num2;
		
		System.out.println("Sum:" + sum);
	}
	
	private static void difference(int num1, int num2) {
		int diff = num1 - num2;
		System.out.println("Difference:" + diff);
	}
	
	private static void multiplication(int num1 , int num2) {
		int mul = num1 * num2;
		System.out.println("Multiplication:" +mul);
	}
	
	private static void division(int num1, int num2) {
		int div = num1/num2;
		System.out.println("Division:" + div);
	}
}

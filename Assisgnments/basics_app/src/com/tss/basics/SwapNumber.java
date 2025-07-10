package com.tss.basics;

import java.util.Scanner;

public class SwapNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the first number: ");
		int num1 = scanner.nextInt();
		
		System.out.println("Enter the second number: ");
		int num2 = scanner.nextInt();
		
		swap(num1, num2);
		swapwithoutthirdvariable(num1, num2);

	}

	private static void swap(int num1, int num2) {
		int swap = num1;
		num1 = num2;
		num2 = swap;
		System.out.println("After swapping first number = " +num1);
		System.out.println("After swapping second number = " +num2);
	}
	
	private static void swapwithoutthirdvariable(int num1, int num2) {
		num1 = num1 + num2;
		num2 = num1 - num2;
		System.out.println("After swapping second number = " +num2);
		num1 = num1 - num2;
		System.out.println("After swapping first number = " +num1);
	}
}

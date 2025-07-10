package com.tss.basics.selectionstatements;

import java.util.Scanner;

public class MaxOfTwoNumbers {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the first number:");
		int num1 = scanner.nextInt();
		
		System.out.println("Enter the second number:");
		int num2 = scanner.nextInt();
		
		maxOfTwoNumbers(num1, num2);
	}

	private static void maxOfTwoNumbers(int num1, int num2) {
		if (num1>num2) {
			System.out.println("The maximum number is: "+num1);
		}
		else {
			System.out.println("The maximum number is: "+num2);
		}
	}
}

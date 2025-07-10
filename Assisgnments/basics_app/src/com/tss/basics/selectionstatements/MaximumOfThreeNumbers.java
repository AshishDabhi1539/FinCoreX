package com.tss.basics.selectionstatements;

import java.util.Scanner;

public class MaximumOfThreeNumbers {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the first number:");
		int num1 = scanner.nextInt();
		
		System.out.println("Enter the second number:");
		int num2 = scanner.nextInt();
		
		System.out.println("Enter the third number:");
		int num3 = scanner.nextInt();
		
		maxOfThreeNumbers(num1, num2, num3);
	}

	private static void maxOfThreeNumbers(int num1, int num2, int num3) {
		if (num1>num2) {
			
			if(num1>num3) {
				System.out.println("The maximum number is: "+num1);
			}
			
			else {
				System.out.println("The maximum number is: "+num3);
			}
		}
		
		
		else {
			System.out.println("The maximum number is: "+num2);
		}
	}
}

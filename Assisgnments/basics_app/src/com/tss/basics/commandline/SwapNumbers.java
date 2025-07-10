package com.tss.basics.commandline;

import java.util.Scanner;

public class SwapNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num1 = Integer.parseInt(args[0]);
		int num2 = Integer.parseInt(args[1]);
		
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

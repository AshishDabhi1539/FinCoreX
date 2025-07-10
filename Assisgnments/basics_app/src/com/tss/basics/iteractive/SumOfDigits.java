package com.tss.basics.iteractive;

import java.util.Scanner;

public class SumOfDigits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number: ");
		int num1 = sc.nextInt();
		
		int sum = 0;
		
		while(num1>0) {
			int digit = num1 % 10;
			sum = sum + digit;
			num1 = num1/10;
		}

		System.out.println("The sum of the number is "+ sum);
	}

}

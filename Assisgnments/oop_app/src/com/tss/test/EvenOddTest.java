package com.tss.test;

import java.util.Scanner;

public class EvenOddTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the number: ");
		int number = scanner.nextInt();
		
		evenOdd(number);

	}

	private static void evenOdd(int number) {
		// TODO Auto-generated method stub
		if(number%2!=0)
		{
			System.out.println("Not Even");
			return;
		}
		System.out.println("Evensssssssssssssssssssssssssssssssss");
	}
	

	
}

package com.tss.basics.iteractive;

import java.util.Scanner;

public class PatternPrimeNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of rows you want: ");
		int rows = sc.nextInt();
		
		int start = 2;
		int i;
		for (i = 0; i<rows; i++) {
			for(int j = 0; j<=i; j++) {
				while(!isPrime(start)) {
					start++;
				}
				System.out.print(start + " ");
				start++;
			}
			System.out.println();
		}
		
		
	}

	private static boolean isPrime(int num) {
		for(int i = 2;i * i <= num; i++) {
			if (num % i == 0) {
				return false;
			}
			
			
		}
		return true;
	}
}

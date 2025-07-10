package com.tss.basics.iteractive;

import java.util.Scanner;

public class PrimeNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the number:");
		int number = scanner.nextInt();
		
		boolean is_prime = true;
		
		if(number<1) {
			is_prime = false;
		}
		
		else {
			int i = 2;
			while(i * i <= number) {
				if (number % i == 0) {
                    is_prime = false; // Found a divisor, so it's not prime
                    break;
                
			}
			i++;
		}

			if (is_prime) {
	            System.out.println(number + " is a prime number.");
	        } else {
	            System.out.println(number + " is not a prime number.");
	        }
	}

}
}

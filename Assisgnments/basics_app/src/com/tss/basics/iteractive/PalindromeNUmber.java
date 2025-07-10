package com.tss.basics.iteractive;

import java.util.Scanner;

public class PalindromeNUmber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		// TODO Auto-generated method stub
		
		System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        int temp = number;
        int reverse = 0;
        
        while(number>0) {
        	int digit = number % 10;
        	reverse = reverse *10 + digit;
        	number = number/10;
        }
        
        if(reverse == temp) {
        	System.out.println("It is a palindrome number.");
        }
        else {
        	System.out.println("Not a palindrome number.");
        }

	}

}

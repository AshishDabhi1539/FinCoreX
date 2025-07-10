package com.tss.basics.iteractive;

import java.util.Scanner;

public class ReverseANumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		// TODO Auto-generated method stub
		
		System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        int reverse_number = 0;
        
        while(number>0) {
        	int remainder = number % 10;
        	reverse_number = reverse_number * 10 + remainder;
        	number = number/10;
        }
        System.out.println("reverse number:" + reverse_number);
	}

}

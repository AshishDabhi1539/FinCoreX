package com.tss.basics.games;

import java.util.Scanner;
import java.util.Random;
public class NumberGuess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		Random rn = new Random();
		
		int random_number = rn.nextInt(100) + 1;
		System.out.println(random_number);
		
		int max_attempt = 5;
		
		
		
		
		while(0<=max_attempt) {
			
			System.out.println("Guess a number from 1 to 100: ");
			int guess = sc.nextInt();
			
			if (max_attempt == 0) {
				System.out.println("You exhaust your attempts");
			}
			
			else {
				if (guess >100 || guess <1) {
					System.out.println("Enter a valid number between 1 and 100");
					continue;
				}
				
				if(guess == random_number) {
					System.out.println("You won: in attempt " + (6 - max_attempt));
					break;
				}
				
				else if(guess > random_number) {
					System.out.println("Too high");
				}
				
				else {
					System.out.println("Too low");
				}
			}
			
			max_attempt--;
	}

}
}

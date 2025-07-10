package com.tss.basics.homework1;

import java.util.Scanner;
public class StringShifting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the string:");
		String original_string = scanner.nextLine();
		
		System.out.println("Enter the number for how much you want to shift: ");
		int shift_number = scanner.nextInt();
		
		String shifted_string = shiftCharacters(original_string, shift_number);
		
		System.out.println("Shifted string: "+ shifted_string);
		

	}
	
	private static String shiftCharacters(String original_string, int number) {
		
		char result[] = new char[original_string.length()];
		for (int i= 0; i<original_string.length(); i++) {
			char character = original_string.charAt(i);
			
			if(character >= 'a' && character<='z') {
				result[i] = (char) ((character - 'a' + number + 26)%26 + 'a');
			}
			
			else if(character >= 'A' && character <= 'Z') {
				result[i] = (char) ((character - 'A' + number + 26)%26 + 'A');
			}
			
			else {
				result[i] = character;
			}
			
			
		}
		return new String(result);	
	}

}

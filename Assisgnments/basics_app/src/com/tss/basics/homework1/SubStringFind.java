package com.tss.basics.homework1;

import java.util.Scanner;

public class SubStringFind {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the first word you want to check: ");
		String first_word = scanner.nextLine();

		System.out.println("Enter the second word if it is sub-string or not: ");
		String second_word = scanner.nextLine();

		System.out.println(isSubString(first_word, second_word));
	}

	private static boolean isSubString(String first_word, String second_word) {

		char original_word[] = first_word.toCharArray();
		char to_find_word[] = second_word.toCharArray();
		
		if(to_find_word.length == 0) return true;
		if(to_find_word.length > original_word.length) return false;

		for (int i = 0; i <= original_word.length - to_find_word.length; i++) {
			int j;
			for (j = 0; j < to_find_word.length; j++) {
				if (original_word[i + j] != to_find_word[j]) {
					break;
				}
			}
			if (j == to_find_word.length) {
				return true;
			}			
		}
		return false;
	}
}


//to point approach should be done for O(n);
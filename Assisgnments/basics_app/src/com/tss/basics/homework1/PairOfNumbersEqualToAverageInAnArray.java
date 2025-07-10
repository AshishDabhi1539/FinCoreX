package com.tss.basics.homework1;

import java.util.Scanner;

public class PairOfNumbersEqualToAverageInAnArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter size of array: ");
		int n = scanner.nextInt();
		int arr [] = new int[n];
		int sum = 0;
		
		System.out.println("Enter Element of array: " );
		for(int i= 0;i<arr.length;i++)
		{
			arr[i] = scanner.nextInt();
			sum += arr[i];
		} 

		int  average_of_array_element = sum/arr.length;
		
		System.out.println("Average of all element os array is "+average_of_array_element);
		
		if(elementSumNumber(average_of_array_element, n))
			System.out.println("True");
		else
			System.out.println("false");
	}

	private static boolean elementSumNumber(int average_of_array_element, int n) {
		for (int i = 0; i<n-1;i++) {
			for(int j = 0; j<i+1;j++) {
				if(i+j == average_of_array_element) {
					return true;
				}
				
			}
		}
		return false;
	}

}

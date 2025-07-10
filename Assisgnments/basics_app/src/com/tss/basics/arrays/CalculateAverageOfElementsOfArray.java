package com.tss.basics.arrays;

import java.util.Scanner;

public class CalculateAverageOfElementsOfArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter size of array:- ");
		int n = scanner.nextInt();
		int arr [] = new int[n];
		int sum = 0;
		
		System.out.println("Enter Element of array:- ");
		for(int i= 0;i<arr.length;i++)
		{
			arr[i] = scanner.nextInt();
		}
		for(int i= 0;i<arr.length;i++)
		{
			sum += arr[i];
		}
		
		double average_of_array_element = sum/arr.length;
		
		System.out.println("Average of all element os array is "+average_of_array_element);
	}

}

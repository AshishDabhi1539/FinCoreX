package com.tss.basics.homework1;

import java.util.Scanner;
public class SquareOfArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the array size: ");
		int n = scanner.nextInt();
		
		
		
		System.out.println("Enter the array in the increasing order: ");
		int array[] = new int[n];
		
		int lenght = array.length;
		
		System.out.println("Enter Element of array:- ");
		for(int i= 0;i<array.length;i++)
		{
			array[i] = scanner.nextInt();
		}
		
		if (arrayInputSortedOrNot(array, lenght)) {
			
			int[] squaredArray = arraySquaredOutput(array, n);
			for (int i = 0; i < squaredArray.length; i++) {
			    System.out.print(squaredArray[i] + " ");
			}
			System.out.println(arraySquaredOutput(array, lenght));
		}
		
		else {
			System.out.println("Please provide the Sorted Array");
		}
		
		
	}

	private static boolean arrayInputSortedOrNot(int array[], int lenght) {
		if(lenght == 1 || lenght== 0) {
			return true;
		}
		return array[lenght-1] >= array[lenght - 2] && arrayInputSortedOrNot(array, lenght-1);
	}
	
	private static int[] arraySquaredOutput(int array[] , int lenght) {
		
			int left = 0;
			int right = lenght -1 ;
			int result[] = new int[lenght];
			int result_index = lenght-1;
			
			while(left<=right) {
				int square_left = array[left] * array[left];
				int square_right = array[right] * array[right];
				
				if (square_left > square_right) {
					result[result_index] = square_left;
					left ++;
				}
				else {
					result[result_index] = square_right;
					right --;
				}
				result_index --;
			}
			
		
		return result;
	}
}

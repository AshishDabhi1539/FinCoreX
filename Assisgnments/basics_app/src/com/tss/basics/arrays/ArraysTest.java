package com.tss.basics.arrays;

import java.util.Scanner;
public class ArraysTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the numbers of array:");
		
		int arr[] = new int[5];
		
		for (int i = 0; i<5; i++) {
			arr[i] = scanner.nextInt();
			}
		
		System.out.println("Elements of array are:");
		
		for(int i = 0; i< 5; i++) {
			System.out.print(arr[i]);
		}
	}

}

package com.tss.basics.arrays;

import java.util.Scanner;

public class Maximum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter size of array:- ");
		int n = scanner.nextInt();
		int arr [] = new int[n];
		int maximum = 0;
		
		System.out.println("Enter Element of array:- ");
		for(int i= 0;i<arr.length;i++)
		{
			arr[i] = scanner.nextInt();
		}
		for(int i= 0;i<arr.length;i++)
		{
			if(arr[i] > maximum)
			{
				maximum = arr[i];
			}
		}
		
		System.out.println("Maximum element of array is:- "+maximum);
	}

}

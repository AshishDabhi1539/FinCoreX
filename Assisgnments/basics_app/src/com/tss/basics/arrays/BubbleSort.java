package com.tss.basics.arrays;

import java.util.Scanner;


public class BubbleSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter size of array:- ");
		int n = scanner.nextInt();
		int arr [] = new int[n];
		
		System.out.println("Enter Element of array:- ");
		for(int i= 0;i<arr.length;i++)
		{
			arr[i] = scanner.nextInt();
		}
		
		for(int i=0;i<arr.length;i++)
		{
			for(int j = 0;j<arr.length-1;j++)
			{
				if(arr[j] > arr[j+1])
				{
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		System.out.println("sorted array:-");
		for(int i= 0;i<arr.length;i++)
		{
			System.out.println(arr[i]);
		}
		
		
	}

}

	
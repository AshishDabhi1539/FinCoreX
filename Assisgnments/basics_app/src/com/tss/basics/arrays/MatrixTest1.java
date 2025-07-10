package com.tss.basics.arrays;

import java.util.Scanner;

public class MatrixTest1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int matrix[][]= new int[3] [3];
		
		Scanner scanner = new Scanner(System.in);
		
		for (int i = 0; i<3; i++) {
			for(int j = 0; j<3;j++) {
				matrix[i][j]= scanner.nextInt();
			}
		}
		System.out.println("Matrix is: ");
		
		for (int i = 0; i<3; i++) {
			for(int j = 0; j<3;j++) {
				System.out.print(matrix[i][j]+"\t");
			}
			System.out.println("");
		}
	}

}

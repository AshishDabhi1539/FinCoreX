package com.tss.test;

import java.util.Scanner;

public class MatrixTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int matrix[][] = new int[3][3];
		
		readMatrix(matrix,scanner);
		printMatrix(matrix);
	}

	private static void printMatrix(int[][] matrix) {
		// TODO Auto-generated method stub
		for(int i=0;i<3;i++)
		{
			printRow(matrix,i);
			System.out.println();
		}
	}

	private static void printRow(int[][] matrix, int i) {
		// TODO Auto-generated method stub
		for(int j=0;j<3;j++)
		{
			
			System.out.print(matrix[i][j]+"\t");
			
		}
	}

	private static void readMatrix(int[][] matrix, Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.println("Enter matrix Element: ");
		for(int i=0;i<3;i++)
		{
			readRow(matrix,scanner,i);
		}
	}

	private static void readRow(int[][] matrix, Scanner scanner, int i) {
		// TODO Auto-generated method stub
		for(int j=0;j<3;j++)
		{
			matrix[i][j] = scanner.nextInt();
		}
	}
	
	
	
	

}

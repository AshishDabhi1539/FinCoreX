package com.tss.basics.arrays;

import java.util.Scanner;

public class AdditionMultiplicationOfMatrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		int matrix1[][] = new int[3][3];
		int matrix2[][] = new int[3][3];
		
		System.out.println("Read first matrix");
		readMatrix(matrix1, scanner);
		
		
		System.out.println("Read second matrix");
		readMatrix(matrix2, scanner);
		
		int sum[][] = addition(matrix1, matrix2);
		System.out.println("Addition");
		printMatrix(sum);

		int multiplication[][] = multiplication(matrix1, matrix2);
		System.out.println("Multiplication");
		printMatrix(multiplication);
		
		
			}
	
	private static void readMatrix(int matrix[][], Scanner scanner){
				
		System.out.println("Enter the first matrix: ");
		for (int i = 0; i<3; i++) {
			for(int j = 0; j<3;j++) {
				matrix[i][j]= scanner.nextInt();
			}
		}
		
		
		
	}
	
	private static void printMatrix(int result_matrix[][]) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(result_matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	private static int[][] multiplication(int matrix1[][], int matrix2[][]) {
		// TODO Auto-generated method stub
		int result_matrix[][] = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					result_matrix[i][j] += matrix1[i][k] * matrix2[k][j];
				}
			}
		}
		return result_matrix;

	}
	
	private static int[][] addition(int matrix1[][], int matrix2[][]) {
		int result_matrix[][] = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				result_matrix[i][j] = matrix1[i][j] + matrix2[i][j];
			}
		}
		return result_matrix;

	}

}


	
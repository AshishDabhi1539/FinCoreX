package com.tss.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FIleReadTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try (FileReader reader = new FileReader("input.txt"); FileWriter writer = new FileWriter("Output.txt");){
			
			int ch;
			
			
			while ((ch = reader.read()) != -1) {
				System.out.print((char)ch);
				writer.write(ch);
				
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
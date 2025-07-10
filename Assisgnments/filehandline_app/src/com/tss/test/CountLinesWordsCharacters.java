package com.tss.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CountLinesWordsCharacters {

	public static void main(String[] args) {
		int charCount = 0;
		int wordCount = 0;
		int lineCount = 0;

		boolean inWord = false;

		try (FileReader reader = new FileReader("input.txt"); FileWriter writer = new FileWriter("Output.txt")) {

			int ch;

			while ((ch = reader.read()) != -1) {
				writer.write(ch);
				System.out.print((char) ch);

				if (ch != ' ') {
					charCount++;
				}
				if (ch == '\n') {
					lineCount++;
				}

				if (Character.isWhitespace(ch)) {
					inWord = false;
				} else {
					if (!inWord) {
						wordCount++;
						inWord = true;
					}
				}
			}

			if (charCount > 0 && ch != '\n') {
				lineCount++;
			}

			System.out.println();
			System.out.println("Characters: " + charCount);
			System.out.println("Words: " + wordCount);
			System.out.println("Lines: " + lineCount);

		} catch (FileNotFoundException e) {
			System.out.println("Input file not found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("An I/O error occurred.");
			e.printStackTrace();
		}
	}
}

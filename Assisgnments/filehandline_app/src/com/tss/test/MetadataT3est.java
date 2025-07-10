package com.tss.test;

import java.io.File;

public class MetadataT3est {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String path = "C:\\Users\\mahek.morzaria\\Downloads";

		File file = new File(path);

		if (!file.exists()) {
			System.out.println("The path does not exist.");
			return;
		}

		processPath(file);

	}

	public static void processPath(File file) {
		if (file.isFile()) {
			System.out.print("File: " + file.getName());
			System.out.println("   ->Size: " + file.length() + " bytes");
		}

		else if (file.isDirectory()) {
			System.out.println("Directory: " + file.getName());
			File[] contents = file.listFiles();
			if (contents != null && contents.length > 0) {
				for (File f : contents) {
					processPath(f);
				}
			}

			else {
				System.out.println("The directory is empty.");
			}
		}

		else {
			System.out.println("Unknown file type: " + file.getName());
		}
	}

}

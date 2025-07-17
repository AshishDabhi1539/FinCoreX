package com.tss.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Product {

	public static void main(String[] args) {
	
		        List<String> words = Arrays.asList("apple", "banana", "apricot", "blueberry", "cherry");

		        Map<Character, List<String>> grouped = words.stream()
		            .collect(Collectors.groupingBy(string -> string.charAt(0)));

		        System.out.println("Grouped by Starting Character:");
		        grouped.forEach((key, value) -> System.out.println(key + " = " + value));
		    
		

	}

}

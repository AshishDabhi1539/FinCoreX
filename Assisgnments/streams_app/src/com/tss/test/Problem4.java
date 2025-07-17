package com.tss.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Problem4 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "apricot", "blueberry", "cherry");

        Map<Character, Long> countByFirstChar = words.stream()
            .collect(Collectors.groupingBy(s -> s.charAt(0), Collectors.counting()));

        System.out.println("Count of Words by Starting Character:");
        countByFirstChar.forEach((k, v) -> System.out.println(k + " = " + v));
    }
}

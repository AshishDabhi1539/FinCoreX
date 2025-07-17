package com.tss.test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Problem7 {
    public static void main(String[] args) {
        List<String> emails = Arrays.asList(
            "mahek@gmail.com", "harshad@yahoo.com", "harshil@gmail.com", "harsh@outlook.com"
        );

        Set<String> domains = emails.stream()
            .map(email -> email.substring(email.indexOf("@") + 1))
            .collect(Collectors.toSet());

        System.out.println("Unique Email Domains:");
        domains.forEach(System.out::println);
    }
}

package com.tss.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.tss.model.annotation.Employee;

public class EmployeeAnnotationTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.tss.model.annotation");

        Employee emp = context.getBean(Employee.class);

        System.out.println(emp);
    }
}

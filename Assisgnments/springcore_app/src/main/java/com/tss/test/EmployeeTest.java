package com.tss.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.tss.model.Employee;
import com.tss.model.Department;

public class EmployeeTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

        Department dept = context.getBean("department", Department.class);
        Employee emp = context.getBean("employee", Employee.class);

        System.out.println(dept);
        System.out.println(emp);
    }
}

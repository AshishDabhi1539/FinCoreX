package com.tss.model.annotation;


import org.springframework.stereotype.Component;

@Component
public class Department {
    private String name = "SE"; 

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department [name=" + name + "]";
    }
}

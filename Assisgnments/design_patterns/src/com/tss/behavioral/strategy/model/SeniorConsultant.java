package com.tss.behavioral.strategy.model;

public class SeniorConsultant implements IRole {
    @Override
    public String description() {
        return "Senior Consultant: Reviews code.";
    }

    @Override
    public String responsibility() {
        return "Review code";
    }
}


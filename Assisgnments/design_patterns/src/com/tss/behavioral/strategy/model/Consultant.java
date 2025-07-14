package com.tss.behavioral.strategy.model;

public class Consultant implements IRole {
    @Override
    public String description() {
        return "Consultant: Writes code.";
    }

    @Override
    public String responsibility() {
        return "Write code";
    }
}

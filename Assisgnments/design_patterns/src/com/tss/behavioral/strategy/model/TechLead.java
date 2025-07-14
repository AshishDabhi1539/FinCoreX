package com.tss.behavioral.strategy.model;

public class TechLead implements IRole {
    @Override
    public String description() {
        return "Tech Lead: Assigns work.";
    }

    @Override
    public String responsibility() {
        return "Assign work";
    }
}

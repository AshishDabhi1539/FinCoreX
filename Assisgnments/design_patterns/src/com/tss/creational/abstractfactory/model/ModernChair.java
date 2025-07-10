package com.tss.creational.abstractfactory.model;

public class ModernChair implements IFurniture {
	
	@Override
    public void describe() {
        System.out.println("Modern Chair: Minimalist with metal legs.");
    }
}


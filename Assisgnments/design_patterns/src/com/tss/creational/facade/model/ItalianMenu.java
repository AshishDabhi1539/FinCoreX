package com.tss.creational.facade.model;

public class ItalianMenu implements IMenu {
    @Override
    public void displayMenu() {
        System.out.println("Italian Menu: Margherita Pizza, Pasta Alfredo, Bruschetta");
    }
}

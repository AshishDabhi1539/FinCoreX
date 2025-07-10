package com.tss.model;

public class Table implements IFurniture {
    private final IStyle style;

    public Table(IStyle style) {
        this.style = style;
    }

    public void describe() {
        System.out.println(style.getStyleName() + " Table: Designed with " + style.getStyleName() + " aesthetics.");
    }
}


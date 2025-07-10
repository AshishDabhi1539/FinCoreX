package com.tss.model;

public class Chair implements IFurniture {
    private final IStyle style;

    public Chair(IStyle style) {
        this.style = style;
    }

    public void describe() {
        System.out.println(style.getStyleName() + " Chair: Custom design based on style.");
    }
}

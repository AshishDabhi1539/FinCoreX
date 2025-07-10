package com.tss.model;

public class Sofa implements IFurniture {
    private final IStyle style;

    public Sofa(IStyle style) {
        this.style = style;
    }

    public void describe() {
        System.out.println(style.getStyleName() + " Sofa: Customized for " + style.getStyleName() + " ambiance.");
    }
}

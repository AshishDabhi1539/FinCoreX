package com.tss.model;

import java.util.ArrayList;

public class Composite implements Component{

	
	String name;

    ArrayList<Component> components;

    public Composite()
    {

    }

    public Composite(String name)
    {
        this.name = name;
        components = new ArrayList<>();
    }

    @Override
    public void showPrice()
    {
        for (Component component : components)
        {
            component.showPrice();
        }
    }

    public void add(Component subComponent)
    {
        components.add(subComponent);
    }

	

}

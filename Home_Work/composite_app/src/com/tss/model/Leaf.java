package com.tss.model;

public class Leaf implements Component{

	String name;
    Double price;
    public Leaf()
    {

    }
    public Leaf(String name,double price)
    {
        this.name = name;
        this.price = price;
    }
    
    
	@Override
	public void showPrice() {
		// TODO Auto-generated method stub
		System.out.println(this.price);
	}

}

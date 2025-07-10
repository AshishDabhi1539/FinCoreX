package com.tss.model.inheritance.model;

public class Box {
	private int width;
	private int height;
	private int depth;
	
	public String message = "Box";
	
	public Box(int width, int height, int depth) {
		this.depth=depth;
		this.height=height;
		this.width=width;
	}
	


	public void display()
	{
		System.out.println("Height: "+height);
		System.out.println("Width: "+width);
		System.out.println("Depth: "+depth);
	}
}

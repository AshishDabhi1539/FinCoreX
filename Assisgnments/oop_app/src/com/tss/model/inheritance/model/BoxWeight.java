package com.tss.model.inheritance.model;

public class BoxWeight extends Box{
	
	private int weight;
	private String message = "BoxWeight";
	
	public BoxWeight(int width, int height, int depth, int weight) {
		super(width, height, depth);
		this.weight = weight;
	}

	public void displayBox()
	{
		System.out.println("Weight: "+this.weight);
		System.out.println(message);
		System.out.println(super.message);
	}
}

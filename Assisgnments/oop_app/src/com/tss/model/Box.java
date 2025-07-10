package com.tss.model;

public class Box {
	
	private int width;
	private int height;
	private int depth;
	
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public void setHeight(int height) {
		this.height =height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public int getHeight() {
		return height;
	}
	public void initialize() {
		width = 10;
		height = 20;
		depth = 30;
	}
	
	public void display() {
		System.out.println("Width: "+width);
		System.out.println("Height: "+height);
		System.out.println("Depth: "+depth);
	}
}

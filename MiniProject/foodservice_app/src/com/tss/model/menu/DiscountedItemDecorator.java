package com.tss.model.menu;

public class DiscountedItemDecorator extends BaseMenuItem {
    

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final BaseMenuItem item;
    private final double discountRate; 

    
    public DiscountedItemDecorator(String id, String name, double price, String description, BaseMenuItem item, double discountRate) {
		super(id, name, price, description);
		// TODO Auto-generated constructor stub
		this.item = item;
        this.discountRate = discountRate;
	}
    

    @Override
    public double getPrice() {
        return Math.round((item.getPrice() * (1 - discountRate)) * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return item.getName() + " [Discounted " + (int)(discountRate * 100) + "%] = " + getPrice();
    }
}

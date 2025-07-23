package com.tss.model.order;

import com.tss.model.menu.BaseMenuItem;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.tss.customer.Customer;

public class Order implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String orderId;
    private final Customer customer;
    private final List<BaseMenuItem> items;
    private final double totalAmount;
    private OrderStatus status;
    private final LocalDateTime timestamp;
    private String assignedDeliveryPartner;

    public Order(Customer customer, List<BaseMenuItem> items, double totalAmount) {
        this.orderId = UUID.randomUUID().toString().substring(0, 8); 
        this.customer = customer;
        this.items = items;
        this.totalAmount = totalAmount;
        this.status = OrderStatus.PLACED;
        this.timestamp = LocalDateTime.now();
    }

 

	public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<BaseMenuItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getAssignedDeliveryPartner() {
        return assignedDeliveryPartner;
    }

    public void setAssignedDeliveryPartner(String partner) {
        this.assignedDeliveryPartner = partner;
    }
}

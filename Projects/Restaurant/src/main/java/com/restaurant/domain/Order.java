package com.restaurant.domain;

import java.util.HashMap;
import java.util.Map;

public class Order {
	
	private Long id;
	private Map<MenuItem,Integer> currentOrder = new HashMap<MenuItem,Integer>();
	private String status;
	private String customer;
	
	public void addToOrder(MenuItem menuItem, int quantity) {
		int currentQuantity = 0;
		if (currentOrder.get(menuItem) != null) currentQuantity = currentOrder.get(menuItem);
		currentOrder.put(menuItem, currentQuantity + quantity);
	}
	
	public Double getOrderTotal() {
		double d = 0d;
		for (MenuItem menuItem : currentOrder.keySet()) {
			d += currentOrder.get(menuItem) * menuItem.getPrice();
		}
		return d;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
			for (MenuItem menuItem : currentOrder.keySet()) {
				sb.append(menuItem.getName() + " : " + currentOrder.get(menuItem) + "<br/>");
			}
		return sb.toString();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	public String getCustomer() {
		return customer;
	}
	
}

package com.exam.dao;

import com.exam.model.Item;
import com.exam.model.OrderItem;

public class OrderDAO {

	//TODO: replace with call to database
	OrderItem[] orderItems = new OrderItem[] {
	        new OrderItem(new Item(5, "tile", 7.27f, true), 25),
	        new OrderItem(new Item(7, "Plumbing", 250.00f, false), 1),
	        new OrderItem(new Item(1, "facet", 150.00f, true), 1),
	        new OrderItem(new Item(6, "wire", 125.00f, true), 1),
	        new OrderItem(new Item(25, "Electrical", 1050.00f, false), 10)};
	
	public OrderItem[] getItems() {
		return orderItems;
		
	}
	
}

package com.exam.service;

import com.exam.dao.OrderDAO;
import com.exam.model.Item;
import com.exam.model.OrderItem;
import com.exam.model.OrderTotal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Represents and Order that contains a collection of items.
 *
 * care should be taken to ensure that this class is immutable since it
 * is sent to other systems for processing that should not be able
 * to change it in any way.
 *
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Exams are us</p>
 * @author Joe Blow
 * @version 1.0
 */
@Service
public final class Order implements Serializable {

	private static final long serialVersionUID = 7962247218032832798L;
	
	private final OrderItem[] orderItems;
    private final Hashtable<Item, Item> items = new Hashtable<Item, Item>();
    
    private OrderDAO orderDAO = new OrderDAO();    

    public Order(OrderItem[] orderItems) {
        this. orderItems = orderItems;
    }

    // Returns the total order cost after the tax has been applied
    public OrderTotal getOrderTotal(float taxRate) {

        float orderTotal = 0f;
        float itemCost = 0f;
        OrderTotal total = new OrderTotal();

        OrderItem[] orderItems = orderDAO.getItems();
        List<OrderItem> items = Arrays.asList(orderItems);
        for (OrderItem item : items) {
            if (item.getItem().isTaxable()) {
                itemCost = (item.getItem().getPrice() * item.getQuantity()) * taxRate;
            } else {
                itemCost = item.getItem().getPrice() * item.getQuantity();
            }
            orderTotal = orderTotal + round(itemCost, 2, BigDecimal.ROUND_UP);
        }
        total.setOrderTotal(orderTotal);
        return total;

    }

    /**
     * Returns a Collection of all items sorted by item name
     * (case-insensitive).
     *
     * @return Collection
     */
    public Collection<OrderItem> getItems() {
    	
    	OrderItem[] orderItems = orderDAO.getItems();    	
    	List<OrderItem> items = Arrays.asList(orderItems);

        //Lambda expression for sorting by OrderItem.item.name (java 8)
        items.sort((OrderItem o1, OrderItem o2)
                -> o1.getItem().getName().compareToIgnoreCase(o2.getItem().getName()));

        return items;

    }

    private static float round(float number, int decimalPlace, int round) {

        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(decimalPlace, round);
        return bd.floatValue();

    }

}

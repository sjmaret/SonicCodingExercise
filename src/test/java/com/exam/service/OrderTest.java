package com.exam.service;

import com.exam.model.Item;
import com.exam.model.OrderItem;
import com.exam.model.OrderTotal;
import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.assertThat;

import java.util.Hashtable;

public class OrderTest extends TestCase {

    private OrderItem[] items;
    private Hashtable<Integer, Item> item = new Hashtable<Integer, Item>();

    @Override
    protected void setUp() throws Exception {

        items = new OrderItem[] {
            new OrderItem(new Item(5, "tile", 7.27f, true), 25),
            new OrderItem(new Item(7, "Plumbing", 250.00f, false), 1),
            new OrderItem(new Item(1, "facet", 150.00f, true), 1),
            new OrderItem(new Item(6, "wire", 125.00f, true), 1),
            new OrderItem(new Item(25, "Electrical", 1050.00f, false), 10)};
    }

    @Test
    public void test_getItems() {

        Order order = new Order(items);
        for (OrderItem item : order.getItems()) {
            System.out.println("name = " + item.getItem().getName());
        }
    }

    @Test
    public void test_getOrderTotal() {

        Order order = new Order(items);
        System.out.println("Expected Total Cost = " + 10795.68f);
        OrderTotal orderTotal = order.getOrderTotal(0.10f);
        System.out.println("Actual Total Cost = " + orderTotal.getOrderTotal());
        assertEquals(orderTotal.getOrderTotal(), 10795.68f);
    }

}

package com.exam.model;

import org.springframework.stereotype.Component;

import com.exam.model.Item;

@Component
public class OrderItem {

    public Item item;
    public Integer quantity;

    public OrderItem(Item item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}

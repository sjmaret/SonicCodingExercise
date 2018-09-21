package com.exam.controller;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.OrderItem;
import com.exam.model.OrderTotal;
import com.exam.service.Order;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
	
	private Order order = new Order(null);
	
	protected OrderController() {}

	/*
     * Calculate the average Score of a Tour.
     *
     * @param tourId
     * @return Tuple of "average" and the average value.
     */
	@RequestMapping(method = RequestMethod.GET, path = "/total/{taxRate}")
    public OrderTotal getOrderTotal(@PathVariable(value = "taxRate") float taxRate) {
    	OrderTotal total = order.getOrderTotal(taxRate);
        return total;
    }

    /**
     * Calculate the average Score of a Tour.
     *
     * @param tourId
     * @return Tuple of "average" and the average value.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/items")
    public Collection<OrderItem> getItems() {
    	Collection<OrderItem> items = order.getItems();
        return items;
    }

    /**
     * Exception handler if NoSuchElementException is thrown in this Controller
     *
     * @param ex
     * @return Error message String.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();

    }

}

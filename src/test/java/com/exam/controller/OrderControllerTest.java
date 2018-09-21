package com.exam.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.exam.model.Item;
import com.exam.model.OrderItem;
import com.exam.model.OrderTotal;
import com.exam.service.Order;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OrderController.class, secure = false)
public class OrderControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private OrderItem[] mockItems = new OrderItem[] {
	        new OrderItem(new Item(5, "tile", 7.27f, true), 25),
	        new OrderItem(new Item(7, "Plumbing", 250.00f, false), 1),
	        new OrderItem(new Item(1, "facet", 150.00f, true), 1),
	        new OrderItem(new Item(6, "wire", 125.00f, true), 1),
	        new OrderItem(new Item(25, "Electrical", 1050.00f, false), 10)};
	
	@Test
	public void getItems() throws Exception {
		
		List<OrderItem> items = Arrays.asList(mockItems);		
		Order order = new Order(mockItems);		
		Order mock = Mockito.mock(Order.class);
		Mockito.when(
				mock.getItems()).thenReturn(items);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/orders/items").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "[{item:{key:25,name:Electrical,price:1050.0,taxable:false},quantity:10},{item:{key:1,name:facet,price:150.0,taxable:true},quantity:1},{item:{key:7,name:Plumbing,price:250.0,taxable:false},quantity:1},{item:{key:5,name:tile,price:7.27,taxable:true},quantity:25},{item:{key:6,name:wire,price:125.0,taxable:true},quantity:1}]";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void getOrderTotal() throws Exception {
		
		OrderTotal orderTotal = new OrderTotal();
		orderTotal.setOrderTotal(10795.68f);
		
		List<OrderItem> items = Arrays.asList(mockItems);
		Order order = new Order(mockItems);
		Order mock = Mockito.mock(Order.class);
		Mockito.when(
				mock.getOrderTotal(Mockito.anyFloat())).thenReturn(orderTotal);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/orders/total/0.10f").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{orderTotal:10795.68f}";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

}

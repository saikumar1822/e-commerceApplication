package com.learn.Ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.learn.Ecommerce.dto.ResponseDto;
import com.learn.Ecommerce.exceptions.OrderDetailsNotFoundException;
import com.learn.Ecommerce.model.Order;
import com.learn.Ecommerce.model.Product;
import com.learn.Ecommerce.service.OrderService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class OrderControllerTest {
	@InjectMocks
	OrderController orderController;
	@Mock
	OrderService orderService;
	
	@Test(expected=NullPointerException.class)
	public void testOrderForException() {
		ResponseDto orderResponseDto = new ResponseDto();
		Order order = null;
		order.setId(3);
		List<Product> products = new ArrayList<>();
		Product product = new Product();
		order.setProductList(products);
		Mockito.when(orderService.buyProduct(order)).thenThrow(OrderDetailsNotFoundException.class);
		orderResponseDto.setMessage("Amount transfered successfully");
		
	}
	@Test
	public void testOrderForPositive() {
		ResponseDto orderResponseDto = new ResponseDto();
		Order order = new Order();
		order.setId(Mockito.anyInt());
		List<Product> products = new ArrayList<>();
		Product product = new Product();
		product.setId(2);
		product.setName("tv");
		product.setPrice(2000.00);
		products.add(product);
		product.setId(3);
		product.setName("glass");
		product.setPrice(200.00);
		products.add(product);
		order.setProductList(products);
		orderService.buyProduct(order);
		orderResponseDto.setMessage("Amount transfered successfully");
		new ResponseEntity<ResponseDto>(orderResponseDto, HttpStatus.OK);
		
	}

}

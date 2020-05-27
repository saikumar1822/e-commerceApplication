package com.learn.Ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.Ecommerce.dto.ResponseDto;
import com.learn.Ecommerce.exceptions.OrderDetailsNotFoundException;
import com.learn.Ecommerce.model.Order;
import com.learn.Ecommerce.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@PostMapping("/order")
	public ResponseEntity<ResponseDto> order(@RequestBody Order order) throws OrderDetailsNotFoundException {
		ResponseDto orderResponseDto = new ResponseDto();
		if(order!=null) {
			orderService.buyProduct(order);
			orderResponseDto.setMessage("Amount transfered successfully");
			return new ResponseEntity<ResponseDto>(orderResponseDto, HttpStatus.OK);

		}else {
			throw new OrderDetailsNotFoundException();
		}
		
		
	}
	

}

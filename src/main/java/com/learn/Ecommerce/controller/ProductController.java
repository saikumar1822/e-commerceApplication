package com.learn.Ecommerce.Controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Ecommerce.DTO.ProductDto;
import com.example.Ecommerce.Exception.ProductNameNotFoundException;
import com.example.Ecommerce.Model.Category;
import com.example.Ecommerce.Model.Product;
import com.example.Ecommerce.Model.User;
import com.example.Ecommerce.Service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping("/products/{productName}")
	public ResponseEntity<List<Product>> viewProductName(@PathVariable String productName ) {
		
		
			List<Product> products = userService.viewProductByName(productName);
			
			return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
			
	}
	
}

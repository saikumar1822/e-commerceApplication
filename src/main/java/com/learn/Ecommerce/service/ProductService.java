package com.learn.Ecommerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Ecommerce.DTO.ProductDto;
import com.example.Ecommerce.Exception.ProductNameNotFoundException;
import com.example.Ecommerce.Model.Category;
import com.example.Ecommerce.Model.Product;
import com.example.Ecommerce.Model.User;
import com.example.Ecommerce.Repository.ProductRepository;
import com.example.Ecommerce.Repository.UserRepository;

@Service
public class UserService {
	
	
	
	@Autowired
	ProductRepository productRepository;


	public List<Product> viewProductByName(String productName) {
		List<Product> products=	productRepository.findByProductNameLike("%" + productName + "%");
		if(products.isEmpty()) {
			throw new ProductNameNotFoundException(productName);
		} else {
			return products;
		}

	}
}
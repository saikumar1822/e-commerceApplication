package com.learn.Ecommerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learn.Ecommerce.Exception.ProductNameNotFoundException;
import com.learn.Ecommerce.Model.Category;
import com.learn.Ecommerce.Model.Product;
import com.learn.Ecommerce.Model.User;
import com.learn.Ecommerce.Repository.ProductRepository;
import com.learn.Ecommerce.Repository.UserRepository;

@Service
public class ProductService {
	
	
	
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
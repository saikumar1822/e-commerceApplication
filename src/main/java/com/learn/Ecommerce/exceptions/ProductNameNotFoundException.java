package com.learn.Ecommerce.Exception;

public class ProductNameNotFoundException extends RuntimeException{
	
	public ProductNameNotFoundException(String productName) {
		super(String.format("productName with %s not found",productName));
	}

}

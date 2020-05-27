package com.learn.Ecommerce.service;

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

import com.learn.Ecommerce.Model.Product;
import com.learn.Ecommerce.Repository.ProductRepository;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductServiceTest {
	
	@InjectMocks
	ProductService productService;
	
	@Mock
	ProductRepository productRepository;
	
	@Test
	public void testViewProductNameForPositive() {
		List<Product> products = new ArrayList();
		
		Product product = new Product();
		product.setProductid(1);
		product.setProductName("watch");
		product.setPrice(10000f);
		products.add(product);
		
		Mockito.when(productRepository.findByProductNameLike("phone")).thenReturn(products);
		
		List<Product> productRe = productService.viewProductByName("phone");
		Assert.assertNotNull(productRe);
		Assert.assertEquals(1,products.size() );
		
	}
	@Test(expected = Exception.class)
	public void testViewProductNameForExce() {
		List<Product> products = new ArrayList();
		
		Product product = new Product();
		product.setProductid(1);
		product.setProductName("watch");
		products.add(product);
		
		Mockito.when(productRepository.findByProductNameLike("phone")).thenThrow(Exception.class);
		
		List<Product> productRe = productService.viewProductByName("phone");
		Assert.assertNotNull(productRe);
		Assert.assertEquals(product.getProductName(), "chudidars");
		
	}
	
	@Test
	public void testViewProductNameForNegative() {
		List<Product> products = new ArrayList();
		
		Product product = new Product();
		product.setProductid(1);
		product.setProductName("watch");
		products.add(product);
		
		product = new Product();
		product.setProductid(2);
		product.setProductName("tv");
		products.add(product);
		
		
		Mockito.when(productRepository.findByProductNameLike("phone")).thenReturn(products);
		
		List<Product> productRe = productService.viewProductByName("iron box");
		Assert.assertNotNull(productRe);
		Assert.assertEquals(product.getProductName(), "tv");
		
	}

}

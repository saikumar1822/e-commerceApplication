package com.learn.Ecommerce.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.learn.Ecommerce.model.Order;
import com.learn.Ecommerce.model.Product;
import com.learn.Ecommerce.repository.OrderRepository;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.Silent.class)
public class OrderServiceTest {
	
	@InjectMocks
	OrderService orderService;
	
	@Mock
	OrderRepository orderRepository;
	
	@Mock
	RestTemplate restTemplate;
	
	@Test
	public void testGetStudentsByReqParamForPositive() {
		Order order = new Order();
		order.setId(101);
		List<Product> products = new ArrayList<>();
		Product product = new Product();
		product.setId(20);
		product.setName("laptop");
		product.setPrice(2000.00);
		products.add(product);
		product.setId(21);
		product.setName("watch");
		product.setPrice(1000.00);
		products.add(product);
		order.setProductList(products);
		order=orderRepository.save(order);
		List<Double> d = products.stream().map(x -> x.getPrice()).collect(Collectors.toList());

		 double sum = d.stream().mapToDouble(Double::doubleValue).sum();
		 int amount=(int)sum;
		 String amount1=Integer.toString(amount);
		String url = "http://localhost:8073/transaction";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		Map<String, String> params = new HashMap<String, String>();
		params.put("accountNumber","2");
		params.put("beneficiaryAccountNumber","12");
		params.put("amount",amount1);
		params.put("customerId","1");
		

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			builder.queryParam(entry.getKey(), entry.getValue());
		}

		String result = restTemplate.getForObject(builder.toUriString(), String.class);
		Assert.assertNull(result);
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetStudentsByReqParamForException() {
		Order order = new Order();
		order.setId(101);
		List<Product> products = new ArrayList<>();
		Product product = new Product();
		product.setId(20);
		product.setName("laptop");
		product.setPrice(2000.00);
		products.add(product);
		product.setId(21);
		product.setName("watch");
		product.setPrice(1000.00);
		products.add(product);
		order.setProductList(products);
		order=orderRepository.save(order);
		List<Double> d = order.getProductList().stream().map(x -> x.getPrice()).collect(Collectors.toList());

		 double sum = d.stream().mapToDouble(Double::doubleValue).sum();
		 int amount=(int)sum;
		 String amount1=Integer.toString(amount);
		String url = "http://localhost:8073/transaction";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		Map<String, String> params = new HashMap<String, String>();
		params.put("accountNumber","2");
		params.put("beneficiaryAccountNumber","12");
		params.put("amount",amount1);
		params.put("customerId","1");
		

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			builder.queryParam(entry.getKey(), entry.getValue());
		}

		String result = restTemplate.getForObject(builder.toUriString(), String.class);
		Assert.assertNull(result);
		
	}


}


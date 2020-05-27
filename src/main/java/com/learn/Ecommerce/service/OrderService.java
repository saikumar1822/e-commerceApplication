package com.learn.Ecommerce.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.learn.Ecommerce.model.Order;
import com.learn.Ecommerce.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public String buyProduct(Order order) {

		order = orderRepository.save(order);

		List<Double> d = order.getProductList().stream().map(x -> x.getPrice()).collect(Collectors.toList());

		double sum = d.stream().mapToDouble(Double::doubleValue).sum();
		int amount = (int) sum;
		String amount1 = Integer.toString(amount);
		String url = "http://localhost:8055/transaction";
		String beneficiaryAccountNumber=""+order.getEcommerceAccountNumber();
		String accountNumber=""+order.getAccountNumber();
		

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		Map<String, String> params = new HashMap<String, String>();
		params.put("accountNumber", accountNumber);
		params.put("beneficiaryAccountNumber",beneficiaryAccountNumber);
		params.put("amount", amount1);
		params.put("customerId", "1");

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			builder.queryParam(entry.getKey(), entry.getValue());
		}

		String result = restTemplate.getForObject(builder.toUriString(), String.class);
		return result;
	}

}

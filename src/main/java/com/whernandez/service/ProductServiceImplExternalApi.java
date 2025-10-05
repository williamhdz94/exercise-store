package com.whernandez.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.whernandez.domain.Product;

@Service("API")
public class ProductServiceImplExternalApi implements ProductService {

	@Override
	public List<Product> getProducts() {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<Product>> responseEntity = restTemplate
				.exchange("http://localhost:8080/tienda/api/v1/products/fake-products", 
						HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
						});
		
		List<Product> products = responseEntity.getBody();
		
		return products;
	}

	@Override
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

}

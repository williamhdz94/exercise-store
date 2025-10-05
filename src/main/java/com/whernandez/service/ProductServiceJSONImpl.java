package com.whernandez.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whernandez.domain.Product;

@Service("JSON")
public class ProductServiceJSONImpl implements ProductService{

	public List<Product> getProducts() {
		List<Product> products;
		try {
			products = new ObjectMapper()
					.readValue(this.getClass().getResourceAsStream("/products.json"), 
							new TypeReference<>() {});
			
			return products;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

}

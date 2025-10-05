package com.whernandez.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.whernandez.domain.Product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Lazy
@Slf4j
@Service("MEMORY")
public class ProductServiceImp implements ProductService {
	
	private List<Product> products = new ArrayList<>(Arrays.asList(
			new Product(1, "Smart TV", 900.00, 7),
		    new Product(2, "Cama", 900.00, 7),
		    new Product(3, "Refrigerador", 2000.00, 7)
			));
	
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

}

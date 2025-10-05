package com.whernandez.service;

import java.util.List;

import com.whernandez.domain.Product;

public interface ProductService {

	public List<Product> getProducts();
	
	public void saveProduct(Product product);
	
}

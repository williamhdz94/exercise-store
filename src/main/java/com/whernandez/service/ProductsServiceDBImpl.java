package com.whernandez.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whernandez.domain.Product;
import com.whernandez.entities.ProductEntity;
import com.whernandez.repository.ProductsRespository;

@Service("DB")
public class ProductsServiceDBImpl implements ProductService{
	
	@Autowired
	private ProductsRespository productsRespository;

	@Override
	public List<Product> getProducts() {
//		TRADICIONAL
//		List<ProductEntity> productEntities = productsRespository.findAll();
//		List<Product> products = new ArrayList<>();
//		
//		for (ProductEntity productEntity : productEntities) {
//			Product product = new Product();
//			product.setId(productEntity.getId());
//			product.setName(productEntity.getName());
//			product.setPrice(productEntity.getPrice());
//			product.setStock(productEntity.getStock());
//			products.add(product);
//		}
//		return products;
		
//		Lambdas
		List<Product> products = productsRespository.findByPriceGreaterThanAndStockLessThan(10000.0, 40)
				.stream().map(productEntity -> {
					Product product = new Product();
					product.setId(productEntity.getId());
					product.setName(productEntity.getName());
					product.setPrice(productEntity.getPrice());
					product.setStock(productEntity.getStock());
					return product;
				}).collect(Collectors.toList());
		
		return products;
	}

	@Override
	public void saveProduct(Product product) {
		// mapeo de Product a ProductEntity
		ProductEntity productEntity = new ProductEntity();
		productEntity.setName(product.getName());
		productEntity.setPrice(product.getPrice());
		productEntity.setStock(product.getStock());
		
		// persistencia
		productsRespository.save(productEntity);
		
	}
	
}

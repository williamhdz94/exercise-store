package com.whernandez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whernandez.entities.ProductEntity;

@Repository
public interface ProductsRespository extends JpaRepository<ProductEntity, Integer>{
	
	// custom method by wildcard JPA
	List<ProductEntity> findByPriceLessThan(Double price);
	
	List<ProductEntity> findByNameLike(String name);
	
	List<ProductEntity> findByPriceGreaterThanAndStockLessThan(Double price, Integer stock);

}

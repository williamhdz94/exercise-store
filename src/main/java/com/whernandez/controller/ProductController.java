package com.whernandez.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.whernandez.configurations.ConfigurationParameters;
import com.whernandez.domain.Product;
import com.whernandez.repository.ProductsRespository;
import com.whernandez.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductsRespository productsRespository;
	
	@Autowired
	@Qualifier("DB")
	@Lazy
	private ProductService productService;
	
	@Autowired
	private ConfigurationParameters configurationParameters;

    ProductController(ProductsRespository productsRespository) {
        this.productsRespository = productsRespository;
    }

	@GetMapping
	public ResponseEntity<?> getProducts() {
		
		System.out.println("Params " + configurationParameters.toString());
		
		List<Product> products = productService.getProducts();
		
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/fake-products")
	public ResponseEntity<?> fakeProductsAPI() {
		List<Product> products = new ArrayList<>(Arrays.asList(
				new Product(1, "Sombrilla", 900.00, 7),
			    new Product(2, "Galletas", 900.00, 7),
			    new Product(3, "Pan", 2000.00, 7)
				));
		
		return ResponseEntity.ok(products);
	}
	
	@PostMapping
	public ResponseEntity<?> saveProduct(@RequestBody Product product) {
		productService.saveProduct(product);
		
		// obteniendo url de servicio
		URI locationUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(product.getId())
				.toUri();
		
		return ResponseEntity.created(locationUri).body(product);
	}
	
	
}

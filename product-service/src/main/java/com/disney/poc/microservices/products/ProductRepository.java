package com.disney.poc.microservices.products;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface ProductRepository extends Repository<Product, Long> {

	public Product findById(Long id);

	@Query("SELECT count(*) from Product")
	public int countProducts();

	public Product save(Product product);
	
	@Query("SELECT p from Product p")
	public List<Product> getAllProducts();
}

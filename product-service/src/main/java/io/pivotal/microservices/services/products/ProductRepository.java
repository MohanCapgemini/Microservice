package io.pivotal.microservices.services.products;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface ProductRepository extends Repository<Product, Long> {

	public Product findByName(String productName);

	@Query("SELECT count(*) from Product")
	public int countProducts();
	
	public Product save(Product product);
}

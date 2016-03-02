package io.pivotal.microservices.services.products;

import java.util.logging.Logger;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * A RESTFul controller for accessing account information.
 */
@RestController
@RequestMapping("/products-service")
public class ProductsController {

	protected Logger logger = Logger.getLogger(ProductsController.class
			.getName());
	
	protected ProductRepository productRepository;

	@Autowired
	public ProductsController(ProductRepository productRepository) {
		this.productRepository = productRepository;

		logger.info("ProductsController says system has "
				+ productRepository.countProducts() + " products");
	}

	@RequestMapping("/products/{productName}")
	public Product byName(@PathVariable("productName") String productName) {

		logger.info("products-service byName() invoked: " + productName);
		Product product = productRepository.findByName(productName);
		logger.info("products-service byNumber() found: " + product);

		if (product == null){
			throw new ProductNotFoundException(productName);
		} else {
			return product;
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add-product")
	public Product create(@RequestBody String body) {

		logger.info("Request :: " + body);
		
			JSONObject jsonObject=JSONObject.fromObject(body);
			
			Product product = new Product();
			product.setDetails(jsonObject.optString("details"));
			product.setName(jsonObject.optString("name"));
			product.setValue(jsonObject.optLong("value"));
			product.setId(jsonObject.optLong("id"));
			
			productRepository.save(product);
			
			logger.info("Id :: "+product.getId());

			return product;
		 
		
	}

}

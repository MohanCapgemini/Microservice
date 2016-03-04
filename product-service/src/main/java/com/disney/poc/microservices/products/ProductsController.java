package com.disney.poc.microservices.products;

import java.util.List;
import java.util.logging.Logger;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * A RESTFul controller
 */
@RestController
@RequestMapping("/products-service")
public class ProductsController {

	protected Logger logger = Logger.getLogger(ProductsController.class.getName());

	protected ProductRepository productRepository;

	@Autowired
	public ProductsController(ProductRepository productRepository) {
		this.productRepository = productRepository;

		logger.info("ProductsController says system has "+ productRepository.countProducts() + " products");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/add-product")
	public ResponseEntity<Product> create(@RequestBody String body) {
		logger.info("Request :: " + body);

		JSONObject jsonObject = JSONObject.fromObject(body);

		Product product = new Product();
		product.setDetails(jsonObject.optString("details"));
		product.setName(jsonObject.optString("name"));
		product.setValue(jsonObject.optLong("value"));
		product.setId(jsonObject.optLong("id"));

		productRepository.save(product);

		logger.info("Saved");

		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@RequestMapping("/product/{productId}")
	public ResponseEntity<Product> byId(@PathVariable("productId") long productId) {

		logger.info("products-service invoked: " + productId);
		Product product = productRepository.findById(productId);
		logger.info("products-service found: " + product);

		if (product == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}
	}
	
	@RequestMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {

		logger.info("getAllProducts invoked");
		List<Product> prodcuts = productRepository.getAllProducts();
		logger.info("getAllProducts found");

		return new ResponseEntity<List<Product>>(prodcuts, HttpStatus.OK);
	}

}

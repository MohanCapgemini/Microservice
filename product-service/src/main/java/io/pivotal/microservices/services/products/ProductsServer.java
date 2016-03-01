package io.pivotal.microservices.services.products;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@SpringBootApplication
@PropertySource("classpath:db-config.properties")
@EntityScan("io.pivotal.microservices.services.products")
@EnableJpaRepositories("io.pivotal.microservices.services.products")
public class ProductsServer {
	
	protected Logger logger = Logger.getLogger(ProductsServer.class.getName());

	@Autowired
    Environment env;

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "products-server");
		SpringApplication.run(ProductsServer.class, args);
	}
	
	@Bean
	public DataSource dataSource() {
		logger.info("dataSource() invoked");

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));

		logger.info("dataSource = " + dataSource);

		return dataSource;
	}
	
}

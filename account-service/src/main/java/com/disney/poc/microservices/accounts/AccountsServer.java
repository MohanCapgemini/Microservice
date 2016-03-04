package com.disney.poc.microservices.accounts;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(AccountsWebApplication.class)
public class AccountsServer {
	
	@Autowired
    Environment env;

	protected Logger logger = Logger.getLogger(AccountsServer.class.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Tell server to look for accounts-server.properties or accounts-server.yml
		System.setProperty("spring.config.name", "accounts-server");

		SpringApplication.run(AccountsServer.class, args);
	}
	
}

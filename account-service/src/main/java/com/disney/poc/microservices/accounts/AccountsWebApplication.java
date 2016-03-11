package com.disney.poc.microservices.accounts;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
@EntityScan("com.disney.poc.microservices.accounts")
@EnableJpaRepositories("com.disney.poc.microservices.accounts")
@PropertySource("classpath:db-config.properties")
public class AccountsWebApplication {

	protected Logger logger = Logger.getLogger(AccountsWebApplication.class.getName());
	
	@Autowired
    Environment env;

	@Bean
	public DataSource dataSource() {
		logger.info("dataSource() invoked");

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver"));
		        dataSource.setUrl(env.getProperty("spring.datasource.url"));
		        dataSource.setUsername(env.getProperty("spring.datasource.username"));
		        dataSource.setPassword(env.getProperty("spring.datasource.password"));


		logger.info("dataSource = " + dataSource);

		// Sanity check
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> accounts = jdbcTemplate
				.queryForList("SELECT number FROM t_account");
		logger.info("System has " + accounts.size() + " accounts");

		return dataSource;
	}
	
}

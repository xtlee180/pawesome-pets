package com.application.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.application")
@EntityScan(basePackages = "com.application.dto")
@EnableJpaRepositories(basePackages = "com.application.dao")
public class ApplicationRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationRestApiApplication.class, args);
	}

}

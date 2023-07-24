package com.pets.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.pets")
@EntityScan(basePackages = "com.pets.dto")
@EnableJpaRepositories(basePackages = "com.pets.model.dao")
public class PetsResApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetsResApiApplication.class, args);
	}

}

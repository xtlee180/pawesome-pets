package com.pawsomepets.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.pawsomepets")
@EntityScan(basePackages = "com.pawsomepets.dto")
@EnableJpaRepositories(basePackages = "com.pawsomepets.model.dao")
public class PetsResApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetsResApiApplication.class, args);
	}

}

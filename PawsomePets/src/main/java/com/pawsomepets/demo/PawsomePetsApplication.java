package com.pawsomepets.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.pawsomepets")
@EntityScan(basePackages = "com.pawsomepets.dto")
@EnableJpaRepositories(basePackages = "com.pawsomepets.dao")
public class PawsomePetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PawsomePetsApplication.class, args);
	}

	@Bean
	public RestTemplate getTemplate() {
		return new RestTemplate();
	}

}

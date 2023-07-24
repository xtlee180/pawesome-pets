package com.wish.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.users")
@EntityScan(basePackages = "com.users.dto")
@EnableJpaRepositories(basePackages = "com.users.dao")
public class UserRestApiSecurityTestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRestApiSecurityTestsApplication.class, args);
	}

}

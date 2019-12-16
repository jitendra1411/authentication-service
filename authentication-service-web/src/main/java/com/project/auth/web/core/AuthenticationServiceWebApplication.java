package com.project.auth.web.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.project.auth.web.*"})
@EntityScan(value = "com.project.auth.persistence.sql.entities")
@EnableJpaRepositories(value = "com.project.auth.persistence.sql.repositories")
public class AuthenticationServiceWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceWebApplication.class, args);
	}

}

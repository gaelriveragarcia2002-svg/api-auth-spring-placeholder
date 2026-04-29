package com.draco_dr.api_auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAuthApplication.class, args);
	}

}

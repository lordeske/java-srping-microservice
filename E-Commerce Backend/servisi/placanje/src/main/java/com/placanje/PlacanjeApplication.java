package com.placanje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PlacanjeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlacanjeApplication.class, args);
	}

}

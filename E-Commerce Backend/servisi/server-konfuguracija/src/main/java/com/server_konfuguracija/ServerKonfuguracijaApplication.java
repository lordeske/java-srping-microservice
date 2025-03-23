package com.server_konfuguracija;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ServerKonfuguracijaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerKonfuguracijaApplication.class, args);
	}

}

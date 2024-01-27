package com.client.ws.rasmooplus;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableCaching
public class WsRasmooPlusApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsRasmooPlusApplication.class, args);
	}

}

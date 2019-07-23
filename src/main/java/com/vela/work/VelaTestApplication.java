package com.vela.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class VelaTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(VelaTestApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder templateBuilder) {
		
		return templateBuilder.build();
	}
}

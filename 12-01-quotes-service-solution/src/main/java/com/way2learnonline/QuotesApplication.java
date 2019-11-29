package com.way2learnonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class QuotesApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(QuotesApplication.class, args);
	}
	

	
	
}


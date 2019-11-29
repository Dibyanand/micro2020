package com.way2learnonline.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
// TODo-1 Use an annotation to enable discovery client

public class QuotesApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(QuotesApplication.class, args);
	}
	
	
}


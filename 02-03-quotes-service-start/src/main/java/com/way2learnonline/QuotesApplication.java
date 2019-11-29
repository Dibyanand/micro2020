package com.way2learnonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
// TODo-1 Use an annotation to enable discovery client
@EnableEurekaClient
public class QuotesApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(QuotesApplication.class, args);
	}
	
	
}


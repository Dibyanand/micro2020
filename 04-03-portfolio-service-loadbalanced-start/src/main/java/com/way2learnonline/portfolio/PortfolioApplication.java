package com.way2learnonline.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories
@EnableDiscoveryClient

public class PortfolioApplication {
	

	
	public static void main(String[] args) {
		SpringApplication.run(PortfolioApplication.class, args);
	}
	
	
	    @Bean
	  // TODO -1 Annotate this bean with @LoadBalanced
	 public   RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
}

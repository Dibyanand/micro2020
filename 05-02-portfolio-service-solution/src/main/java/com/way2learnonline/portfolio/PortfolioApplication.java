package com.way2learnonline.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories
@EnableDiscoveryClient
@EnableHystrix
@EnableCircuitBreaker
public class PortfolioApplication {
	

	
	public static void main(String[] args) {
		SpringApplication.run(PortfolioApplication.class, args);
	}
	
	
	    @Bean
	    @LoadBalanced
	 public   RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
}

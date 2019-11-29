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
//TODO-3 Uncomment the below so that proxies will be created for any interface annotated with @FeignClient

//@Enable

public class PortfolioApplication {
	

	
	public static void main(String[] args) {
		SpringApplication.run(PortfolioApplication.class, args);
	}
	
	
	    @Bean
	 public   RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
}

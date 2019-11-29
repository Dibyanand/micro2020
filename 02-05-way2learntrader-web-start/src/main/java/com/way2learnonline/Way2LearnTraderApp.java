package com.way2learnonline;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient

@EnableScheduling

public class Way2LearnTraderApp {

	
    public static void main(String[] args) {
        SpringApplication.run(Way2LearnTraderApp.class, args);
    }
    
    
    @Bean
 public   RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

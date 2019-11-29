package com.way2learnonline.portfolio.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired	
	private RestTemplate restTemplate;

	
	@Autowired
	private LoadBalancerClient loadBalancerClient;


	@Value("${pivotal.accountsService.name}")
	protected String accountsService;

	@HystrixCommand(fallbackMethod = "decreaseBalanceFallBack",
            commandProperties = {@HystrixProperty(name="execution.timeout.enabled", value="false")})
	public ResponseEntity<Double> decreaseBalance(String userId, double amount) {
		
		ServiceInstance serviceInstance=loadBalancerClient.choose(accountsService);
		URI uri=serviceInstance.getUri();
		
		ResponseEntity<Double> result = restTemplate.getForEntity(uri.toString()
				+ "/accounts/{userid}/decreaseBalance/{amount}",
				Double.class, userId, amount);
		
		return result;
		
	}
	
	public ResponseEntity<Double> decreaseBalanceFallBack(String userId, double amount) {
		
		System.err.println("AccountServiceImpl.decreaseBalanceFallBack()===================");
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@HystrixCommand(fallbackMethod = "increaseBalanceFallBack",
            commandProperties = {@HystrixProperty(name="execution.timeout.enabled", value="false")})
	public ResponseEntity<Double> increaseBalance(String userId, double amount) {
		ServiceInstance serviceInstance=loadBalancerClient.choose(accountsService);
		URI uri=serviceInstance.getUri();
		
		ResponseEntity<Double> result =
				restTemplate.getForEntity(uri.toString()+ "/accounts/{userid}/increaseBalance/{amount}",
										Double.class, userId, amount);
		return result;
	}
	
	public ResponseEntity<Double> increaseBalanceFallBack(String userId, double amount) {
		System.err.println("AccountServiceImpl.increaseBalanceFallBack()===================");
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
	

}

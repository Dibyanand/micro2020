package com.way2learnonline.portfolio.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceFallback implements AccountService {
	

	public ResponseEntity<Double> decreaseBalance(String userId, double amount) {
		
		System.err.println("AccountServiceImpl.decreaseBalanceFallBack()===================");
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	public ResponseEntity<Double> increaseBalance(String userId, double amount) {
		System.err.println("AccountServiceImpl.increaseBalanceFallBack()===================");
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	

}

package com.way2learnonline.portfolio.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;


public interface AccountService {
	
	
	public ResponseEntity<Double> decreaseBalance(@PathVariable("userId") final String userId, @PathVariable("amount") final double amount) ;

	
	public ResponseEntity<Double> increaseBalance(@PathVariable("userId") final String userId, @PathVariable("amount") final double amount) ;

}

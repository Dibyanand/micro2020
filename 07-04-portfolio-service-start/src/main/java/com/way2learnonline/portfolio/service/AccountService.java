package com.way2learnonline.portfolio.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//TODO-3 modify the below annotation such that the proxy will look up for gateway in eureka
@FeignClient(name="accounts-service",fallback=AccountServiceFallback.class)
public interface AccountService {
	
	//TODO-4 modify the below annpotation such that the request goes through gateway 
	@RequestMapping(value = "/accounts/{userId}/decreaseBalance/{amount}", method = RequestMethod.GET,produces={"application/json"})
	public ResponseEntity<Double> decreaseBalance(@PathVariable("userId") final String userId, @PathVariable("amount") final double amount) ;

	//TODO-5 modify the below annpotation such that the request goes through gateway 
	@RequestMapping(value = "/accounts/{userId}/increaseBalance/{amount}", method = RequestMethod.GET,produces={"application/json"})
	public ResponseEntity<Double> increaseBalance(@PathVariable("userId") final String userId, @PathVariable("amount") final double amount) ;

}

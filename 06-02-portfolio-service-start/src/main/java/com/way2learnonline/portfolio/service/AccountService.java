package com.way2learnonline.portfolio.service;

//import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@FeignClient(name="accounts-service",fallback=AccountServiceFallback.class)
public interface AccountService {
	
	@RequestMapping(value = "/accounts/{userId}/decreaseBalance/{amount}", method = RequestMethod.GET,produces={"application/json"})
	public ResponseEntity<Double> decreaseBalance(@PathVariable("userId") final String userId, @PathVariable("amount") final double amount) ;

	@RequestMapping(value = "/accounts/{userId}/increaseBalance/{amount}", method = RequestMethod.GET,produces={"application/json"})
	public ResponseEntity<Double> increaseBalance(@PathVariable("userId") final String userId, @PathVariable("amount") final double amount) ;

}

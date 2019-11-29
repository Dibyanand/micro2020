package com.way2learnonline.portfolio.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.way2learnonline.portfolio.domain.Quote;

//TODO-1 modify the below annotation such that the proxy will look up for gateway in eureka

@FeignClient(name="quotes-service",fallback=QuoteServiceCallback.class)
public interface QuoteService {

	//TODO-2 modify the below annpotation such that the request goes through gateway 
	@RequestMapping(value = "/quotes", method = RequestMethod.GET)
	public ResponseEntity<List<Quote>> getQuotes(@RequestParam(value="q", required=false) String query)  ;
	
}

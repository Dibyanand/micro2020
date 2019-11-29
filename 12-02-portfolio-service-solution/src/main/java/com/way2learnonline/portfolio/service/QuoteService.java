package com.way2learnonline.portfolio.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.way2learnonline.portfolio.domain.Quote;


// TODO-1 Change the name of the feign client so that it will look up for zuul server
@FeignClient(name="quotes-service",fallback=QuoteServiceCallback.class)
public interface QuoteService {

	// TODO-2 Change the request mapping value appropriately so that zuul will route to quotes-service 
	@RequestMapping(value = "/quotes", method = RequestMethod.GET)
	public ResponseEntity<List<Quote>> getQuotes(@RequestParam(value="q", required=false) String query)  ;
	
}

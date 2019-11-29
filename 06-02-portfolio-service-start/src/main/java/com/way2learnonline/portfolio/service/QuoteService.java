package com.way2learnonline.portfolio.service;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.way2learnonline.portfolio.domain.Quote;


//TODO -2 uncomment the below annotation so that a proxy implementing this interface can be generated. 
//That proxy will have logic to make restful calls based on the @RequestMapping annotation

//@FeignClient(name="quotes-service",fallback=QuoteServiceCallback.class)
public interface QuoteService {

	@RequestMapping(value = "/quotes", method = RequestMethod.GET)
	public ResponseEntity<List<Quote>> getQuotes(@RequestParam(value="q", required=false) String query)  ;
	
}

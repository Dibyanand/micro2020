package com.way2learnonline.portfolio.service;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.way2learnonline.portfolio.domain.Quote;

@Service
public class QuoteServiceImpl implements QuoteService {
	
	@Value("${pivotal.quotesService.name}")
	private String quotesService;

	@Autowired	
	private RestTemplate restTemplate;
	

	
	
// TODO -2 Annotate this method so that if this method thows an exception, getQuotesFallback should be executed.
	// Also, Configure  execution.timeout.enabled property to false. can you tell why this is required?
	
	/*@HystrixCommand(fallbackMethod = "getQuotesFallback",
            commandProperties = {@HystrixProperty(name="execution.timeout.enabled", value="false")})*/

	
	public ResponseEntity<List<Quote>> getQuotes(String symbols) {
		
			
		Quote[] quotesArr = restTemplate.getForObject("http://"+quotesService+ "/quotes?q={symbols}", Quote[].class, symbols);
		List<Quote> quotes = Arrays.asList(quotesArr);
		
		return new ResponseEntity<>(quotes, HttpStatus.OK);
	
	}
	
	
	 @SuppressWarnings("unused")
	    private ResponseEntity<List<Quote>> getQuotesFallback(String symbols) {
	        List<Quote> quotes = new ArrayList<>();
	        String[] splitSymbols = symbols.split(",");

	        for (String symbol : splitSymbols) {
	            Quote quote = new Quote();
	            quote.setSymbol(symbol);
	            quote.setStatus("FAILED");
	            quote.setLastPrice(new BigDecimal(0));
	            quotes.add( quote );
	        }
	        
	        return new ResponseEntity<>(quotes, HttpStatus.OK);
	    }

}

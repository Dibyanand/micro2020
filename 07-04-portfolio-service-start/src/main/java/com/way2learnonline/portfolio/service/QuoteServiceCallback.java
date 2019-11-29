package com.way2learnonline.portfolio.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.way2learnonline.portfolio.domain.CompanyInfo;
import com.way2learnonline.portfolio.domain.Quote;

@Service
public class QuoteServiceCallback implements QuoteService {
	
	public ResponseEntity<List<CompanyInfo>>  getCompanies(String name) {
		return new ResponseEntity<List<CompanyInfo>>(new ArrayList<CompanyInfo>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}


	 public ResponseEntity<List<Quote>> getQuotes(String symbols) {
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

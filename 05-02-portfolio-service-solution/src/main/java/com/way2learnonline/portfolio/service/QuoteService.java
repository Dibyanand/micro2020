package com.way2learnonline.portfolio.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.way2learnonline.portfolio.domain.Quote;




public interface QuoteService {

	
	public ResponseEntity<List<Quote>> getQuotes(String query)  ;
	
}

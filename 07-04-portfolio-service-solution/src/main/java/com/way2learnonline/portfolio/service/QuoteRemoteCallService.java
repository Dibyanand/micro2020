package com.way2learnonline.portfolio.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.way2learnonline.portfolio.domain.Quote;



@Service
public class QuoteRemoteCallService {

	private static final Logger logger = LoggerFactory.getLogger(QuoteRemoteCallService.class);

	@Autowired
	private QuoteService quoteService;


   
	public List<Quote> getQuotes(String symbols) {
		logger.debug("retrieving multiple quotes: " + symbols);		
	
	
		List<Quote> quotes = quoteService.getQuotes(symbols).getBody();
		logger.debug("Received quotes: {}",quotes);
		return quotes;
	}


	public List<Quote> getQuotes(Collection<String> symbols) {
		logger.debug("Fetching multiple quotes array: {} ",symbols);
		StringBuilder builder = new StringBuilder();
		for (Iterator<String> i = symbols.iterator(); i.hasNext();) {
			builder.append(i.next());
			if (i.hasNext()) {
				builder.append(",");
			}
		}
		return getQuotes(builder.toString());
	}

  
}

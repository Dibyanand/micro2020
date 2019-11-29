package com.way2learnonline.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.way2learnonline.domain.CompanyInfo;
import com.way2learnonline.domain.Order;
import com.way2learnonline.domain.Portfolio;
import com.way2learnonline.domain.Quote;
import com.way2learnonline.exception.OrderNotSavedException;
import com.way2learnonline.exception.SymbolNotFoundException;




@Service

public class MarketService {
	private static final Logger logger = LoggerFactory.getLogger(MarketService.class);


/*    @Value("${pivotal.quotesService.name:quotes-service}")
	private String quotesService;
	*/
	@Autowired
	private  QuoteService quoteService;
	
	@Autowired
   private PortfolioService portfolioService;

	
	public List<CompanyInfo> getCompanies(String name) {
		logger.debug("Fetching companies with name or symbol matching: " + name);
		List<CompanyInfo> companyInfoList=   quoteService.getCompanyInfo(name);
		
		return companyInfoList;
		
		
	}

	
	/**
	 * Retrieve multiple quotes.
	 * 
	 * @param symbols comma separated list of symbols.
	 * @return
	 * @throws SymbolNotFoundException 
	 */

	public List<Quote> getQuotes(String symbols) throws SymbolNotFoundException {
		logger.debug("retrieving multiple quotes: " + symbols);
		
		List<Quote> quotes=	quoteService.getQuotes(symbols);
	
		logger.debug("Received quotes: {}",quotes);
		return quotes;
	}


	public List<Quote> getQuotes(String[] symbols) throws SymbolNotFoundException {
		logger.debug("Fetching multiple quotes array: {} ",Arrays.asList(symbols));
		
		return getQuotes(Arrays.asList(symbols));
	}

	public List<Quote> getQuotes(Collection<String> symbols) throws SymbolNotFoundException {
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

    public Order sendOrder(Order order ) throws OrderNotSavedException{
		logger.debug("send order: " + order);
		
		//check result of http request to ensure its ok.
		
		 order=portfolioService.addOrder(order);
		
		logger.debug("Order saved:: " + order);
		return order;
	}
	
	
	public Portfolio getPortfolio(String accountId) throws SymbolNotFoundException {
		
		Portfolio folio = portfolioService.getPortfolio(accountId);
		logger.debug("Portfolio received: " + folio);
		return folio;
	}

	

}

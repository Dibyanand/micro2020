package com.way2learnonline.portfolio.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.way2learnonline.portfolio.domain.CompanyInfo;
import com.way2learnonline.portfolio.domain.Quote;



@FeignClient(name="gateway",fallback=QuoteServiceCallback.class)
public interface QuoteService {

	@RequestMapping(value = "/api/quotes-service/quotes", method = RequestMethod.GET)	
	public ResponseEntity<List<Quote>> getQuotes(@RequestParam(value="q", required=false) String query)  ;
	
	@RequestMapping(value = "/api/quotes-service/company/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<CompanyInfo>> getCompanies(@PathVariable("name") final String name) ;
}

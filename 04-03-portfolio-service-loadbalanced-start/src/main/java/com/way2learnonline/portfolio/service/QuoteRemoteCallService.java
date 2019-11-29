package com.way2learnonline.portfolio.service;

import java.net.URI;
import java.util.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.way2learnonline.portfolio.domain.Quote;



@Service
public class QuoteRemoteCallService {

	private static final Logger logger = LoggerFactory.getLogger(QuoteRemoteCallService.class);

	@Value("${pivotal.quotesService.name}")
	private String quotesService;

	@Autowired	
	private RestTemplate restTemplate;
	

	// TODO-2 Comment the below LoadBalancerClient

	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	
	
 /*   public String getServiceURI(String serviceName){
		
		return "http://"+serviceName;
	}
*/

	/**
	 * Retrieve multiple quotes.
	 * 
	 * @param symbols comma separated list of symbols.
	 * @return
	 */
   
	public List<Quote> getQuotes(String symbols) {
		logger.debug("retrieving multiple quotes: " + symbols);
		
		
		ServiceInstance serviceInstance=loadBalancerClient.choose(quotesService);
		URI uri=serviceInstance.getUri();	
		
		// TODO -3 Comment the above 2 lines

		
		System.out.println("QuoteRemoteCallService.getQuotes() .URI========="+uri);
		
		// TODO -4 replace uri below with http://+quotesService
		
		Quote[] quotesArr = restTemplate.getForObject(uri+ "/quotes?q={symbols}", Quote[].class, symbols);
		List<Quote> quotes = Arrays.asList(quotesArr);
		logger.debug("Received quotes: {}",quotes);
		return quotes;
	}

	/**
	 * Retrieve multiple quotes.
	 * 
	 * @param symbols
	 * @return
	 */
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

    @SuppressWarnings("unused")
    private List<Quote> getQuoteFallback(String symbols) {
        List<Quote> result = new ArrayList<>();
        String[] splitSymbols = symbols.split(",");

        for (String symbol : splitSymbols) {
            Quote quote = new Quote();
            quote.setSymbol(symbol);
            quote.setStatus("FAILED");
            result.add( quote );
        }
        return result;
    }
}

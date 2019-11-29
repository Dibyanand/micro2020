package com.way2learnonline.quotes.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.way2learnonline.domain.CompanyInfo;
import com.way2learnonline.domain.Quote;
import com.way2learnonline.domain.QuoteMapper;
import com.way2learnonline.domain.YahooQuote;
import com.way2learnonline.domain.YahooQuoteResponses;
import com.way2learnonline.quotes.exception.SymbolNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class QuoteService {
	
	@Value("${pivotal.quotes.quotes_url}")
	protected String quote_url;
	@Value("${pivotal.quotes.companies_url}")
	protected String company_url;

	@Value("${pivotal.quotes.yahoo_rest_query}")
	protected String yahoo_url;// = "https://query.yahooapis.com/v1/public/yql?q=select * from yahoo.finance.quotes where symbol in ('{symbol}')&format={fmt}&env={env}";
	
	//https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22ibm%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=

	@Value("${pivotal.quotes.yahoo_env}")
	protected String ENV = "http://datatables.org/alltables.env";

	public static final String FMT = "json";

	private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);


	private RestTemplate restTemplate;

    public QuoteService() {
        restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setObjectMapper(objectMapper);
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(messageConverter);
        restTemplate.setMessageConverters(messageConverters);
    }

    /**
     * Retrieve one or more quotes.
     *
     * @param symbols comma delimited list of symbols.
     * @return a list of quotes.
     */
    
    public Float getRandomFloat(Float f) {
    	Integer i= f.intValue();
    	return i.floatValue();
    }
    
    public List<Quote> getQuotes(String symbols) throws SymbolNotFoundException {
        logger.info("retrieving quotes for: " + symbols);
        if ( symbols.isEmpty() ) return new ArrayList<>();

       /* YahooQuoteResponses responses = restTemplate.getForObject(yahoo_url, YahooQuoteResponses.class, symbols, FMT, ENV);
        logger.debug("Got responses: " + responses);
        List<YahooQuote> yahooQuotes = responses.getResults().getQuoteList().getQuote();
        Date createDate = responses.getResults().getCreated();
        
        

        List<Quote> quotes = yahooQuotes.stream()
                .map(yQuote -> QuoteMapper.INSTANCE.mapFromYahooQuote(yQuote, createDate))
                .collect(Collectors.toList());
        */
        
        String[] symbolList=symbols.split(",");
        
        /*
         * {
            "Name":"Apple Inc",
            "Symbol":"AAPL",
            "LastPrice":524.49,
            "Change":15.6,
            "ChangePercent":3.06549549018453,
            "Timestamp":"Wed Oct 23 13:39:19 UTC-06:00 2013",
            "MSDate": 41570.568969907,
            "MarketCap":476497591530,
            "Volume":397562,
            "ChangeYTD":532.1729,
            "ChangePercentYTD":-1.44368493773359,
            "High":52499,
            "Low":519.175,
            "Open":519.175
        }
         */
        
        Random random= new Random();
        List<Quote> finalQuotes= new ArrayList<Quote>();
        for(String symbol:symbolList){
        	
        	Quote quote= new Quote();
        	quote.setChangePercent(getRandomFloat(random.nextFloat()*100));
        	quote.setChangePercentYTD(-1.44F);
        	quote.setChange(new BigDecimal("2.5"));
        	quote.setChangeYTD(getRandomFloat(random.nextFloat()*1000));
        	quote.setHigh(new BigDecimal(getRandomFloat(random.nextFloat()*1000)));
        	quote.setLastPrice(new BigDecimal(getRandomFloat(random.nextFloat()*1000)));
        	quote.setLow(new BigDecimal(getRandomFloat(random.nextFloat()*1000)));
    		quote.setMarketCap(new Float("476497591530"));
    		quote.setmSDate(null);
    		quote.setName(symbol);
    		quote.setOpen(new BigDecimal(getRandomFloat(random.nextFloat()*1000)));
    		quote.setStatus("SUCCESS");
    		quote.setSymbol(symbol);
    		quote.setTimestamp(new Date());
    		quote.setVolume(new Integer("397562"));
    		finalQuotes.add(quote);
        }

       
        return finalQuotes;
    }
 
   /* public List<Quote> getQuotes(String symbols) throws SymbolNotFoundException {
        logger.debug("retrieving quotes for: " + symbols);
        if ( symbols.isEmpty() ) return new ArrayList<>();

        YahooQuoteResponses responses = restTemplate.getForObject(yahoo_url, YahooQuoteResponses.class, symbols, FMT, ENV);
        logger.debug("Got responses: " + responses);
        List<YahooQuote> yahooQuotes = responses.getResults().getQuoteList().getQuote();
        Date createDate = responses.getResults().getCreated();
        
        

        List<Quote> quotes = yahooQuotes.stream()
                .map(yQuote -> QuoteMapper.INSTANCE.mapFromYahooQuote(yQuote, createDate))
                .collect(Collectors.toList());
        
        List<Quote> finalQuotes= new ArrayList<Quote>();

        for (Quote quote : quotes) {
            if ( quote.getName() != null ) {
            	finalQuotes.add(quote);
            }
        }
        return finalQuotes;
    }*/
    
    
	public List<Quote> getQuotes(Collection<String> symbols) throws SymbolNotFoundException  {
		logger.info("Fetching multiple quotes array: {} ",symbols);
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
	private List<Quote> getQuotesFallback(String symbols) {
		logger.info("QuoteService.getQuoteFallback: circuit opened for symbols: " + symbols);
		throw new RuntimeException("Quote service unavailable.");
	}

    /**
     * Retrieves a list of CompanyInfo objects. Given the name parameters, the
     * return list will contain objects that match the search both on company
     * name as well as symbol.
     *
     * @param name
     *            The search parameter for company name or symbol.
     * @return The list of company information.
     */
    public List<CompanyInfo> getCompanyInfo(String name) {
        logger.info("QuoteService.getCompanyInfo: retrieving info for: " + name);
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        CompanyInfo[] companies = restTemplate.getForObject(company_url,
                CompanyInfo[].class, params);
        logger.info("QuoteService.getCompanyInfo: retrieved info: " + Arrays.toString(companies));
        return Arrays.asList(companies);
    }

	@SuppressWarnings("unused")
	private List<CompanyInfo> getCompanyInfoFallback(String symbol)
			throws SymbolNotFoundException {
		logger.info("QuoteService.getCompanyInfoFallback: circuit opened for symbol: "
				+ symbol);
		throw new RuntimeException("Company Info service unavailable.");
	}
}

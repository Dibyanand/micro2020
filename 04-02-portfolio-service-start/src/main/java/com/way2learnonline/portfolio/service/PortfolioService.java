package com.way2learnonline.portfolio.service;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.way2learnonline.portfolio.domain.Holding;
import com.way2learnonline.portfolio.domain.Order;
import com.way2learnonline.portfolio.domain.OrderType;
import com.way2learnonline.portfolio.domain.Portfolio;
import com.way2learnonline.portfolio.domain.Quote;
import com.way2learnonline.portfolio.repository.OrderRepository;




@Service
public class PortfolioService {
	private static final Logger logger = LoggerFactory.getLogger(PortfolioService.class);

	/**
	 * The order repository to store Order objects.
	 */
	@Autowired
	OrderRepository repository;

	/**
	 * The service than handles the calls to get quotes.
	 */
	@Autowired
	QuoteRemoteCallService quoteService;

	@Autowired	
	private RestTemplate restTemplate;
	
	
	
	
	//TODO-2 Comment the below discovery client declaration  and declare LoadBalancerClient . AutoWire it.
	
	@Autowired
	private DiscoveryClient discoveryClient;


	@Value("${pivotal.accountsService.name}")
	protected String accountsService;

	/**
	 * Retrieves the portfolio for the given accountId.
	 * 
	 * @param accountId
	 *            The account id to retrieve for.
	 * @return The portfolio.
	 */
	public Portfolio getPortfolio(String accountId) {
		/*
		 * Retrieve all orders for accounts id and build portfolio. - for each
		 * order create holding. - for each holding find current price.
		 */
		logger.debug("Getting portfolio for accountId: " + accountId);
		List<Order> orders = repository.findByAccountId(accountId);
		Portfolio folio = new Portfolio();
		folio.setAccountId(accountId);
		return createPortfolio(folio, orders);
	}

	/**
	 * Builds a portfolio object with the list of orders.
	 * 
	 * @param portfolio
	 *            the portfolio object to build.
	 * @param orders
	 *            the list of orders.
	 * @return the portfolio object
	 */
	private Portfolio createPortfolio(Portfolio portfolio, List<Order> orders) {
		
		Set<String> symbols = new HashSet<>();
		Holding holding = null;
		for (Order order : orders) {
			holding = portfolio.getHolding(order.getSymbol());
			if (holding == null) {
				holding = new Holding();
				holding.setSymbol(order.getSymbol());
				portfolio.addHolding(holding);
				symbols.add(order.getSymbol());
			}
			holding.addOrder(order);
		}
		
		List<Quote> quotes = quoteService.getQuotes(symbols);

		for (Quote quote : quotes) {
			portfolio.getHolding(quote.getSymbol()).setCurrentValue(quote.getLastPrice());
		}
		portfolio.refreshTotalValue();
		logger.debug("Portfolio: " + portfolio);
		return portfolio;
	}

	/**
	 * Add an order to the repository and modify account balance.
	 * 
	 * @param order
	 *            the order to add.
	 * @return the saved order.
	 */
	@Transactional
	public Order addOrder(Order order) {
		
		// TODO -3  Initialize the below uri using the ServiceInstance obtained from LoadBalancerClient
		
		List<ServiceInstance> instances=  discoveryClient.getInstances(accountsService);
		URI uri=instances.get(0).getUri();
		
	/*	ServiceInstance serviceInstance=loadbalancerClient.choose(accountsService);
		URI uri=serviceInstance.getUri();*/
		
		System.out.println("PortfolioService.addOrder() .URI========="+uri);
		
		logger.debug("Adding order: " + order);
		if (order.getOrderFee() == null) {
			order.setOrderFee(Order.DEFAULT_ORDER_FEE);
			logger.debug("Adding Fee to order: " + order);
		}
		if (order.getOrderType() == OrderType.BUY) {
			double amount = order.getQuantity()	* order.getPrice().doubleValue()+ order.getOrderFee().doubleValue();
	
			
			ResponseEntity<Double> result = restTemplate.getForEntity(uri.toString()
					+ "/accounts/{userid}/decreaseBalance/{amount}",
					Double.class, order.getAccountId(), amount);
			if (result.getStatusCode() == HttpStatus.OK) {
				logger.info(String
						.format("Account funds updated successfully for account: %s and new funds are: %s",
								order.getAccountId(), result.getBody()));
				return repository.save(order);
			} else {
				// TODO: throw exception - not enough funds!
				// SK - Whats the expected behaviour?
				logger.warn("PortfolioService:addOrder - decresing balance HTTP not ok: ");
				return null;
			}
		} else {
			double amount = order.getQuantity()	* order.getPrice().doubleValue()- order.getOrderFee().doubleValue();
			ResponseEntity<Double> result = restTemplate.getForEntity(uri.toString()+ "/accounts/{userid}/increaseBalance/{amount}",
					Double.class, order.getAccountId(), amount);
			if (result.getStatusCode() == HttpStatus.OK) {
				logger.info(String
						.format("Account funds updated successfully for account: %s and new funds are: %s",
								order.getAccountId(), result.getBody()));
				return repository.save(order);
			} else {
				// TODO: throw exception - negative value???
				logger.warn("PortfolioService:addOrder - increasing balance HTTP not ok: ");
				return null;
			}
		}
	}
}

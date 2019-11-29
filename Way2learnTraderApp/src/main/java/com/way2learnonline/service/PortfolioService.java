package com.way2learnonline.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.way2learnonline.domain.Account;
import com.way2learnonline.domain.Holding;
import com.way2learnonline.domain.Order;
import com.way2learnonline.domain.OrderType;
import com.way2learnonline.domain.Portfolio;
import com.way2learnonline.domain.Quote;
import com.way2learnonline.exception.OrderNotSavedException;
import com.way2learnonline.exception.SymbolNotFoundException;
import com.way2learnonline.repository.OrderRepository;




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
	QuoteService quoteService;
	
	@Autowired
	private AccountService accountService;


	// @Value("${pivotal.quotesService.name}")
	// protected String quotesService;

	

	/**
	 * Retrieves the portfolio for the given accountId.
	 * 
	 * @param accountId
	 *            The account id to retrieve for.
	 * @return The portfolio.
	 * @throws SymbolNotFoundException 
	 */
	public Portfolio getPortfolio(String accountId) throws SymbolNotFoundException {
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
	 * @throws SymbolNotFoundException 
	 */
	private Portfolio createPortfolio(Portfolio portfolio, List<Order> orders) throws SymbolNotFoundException {
		// TODO: change to forEach() and maybe in parallel?
		
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
		logger.debug("Adding order: " + order);
		if (order.getOrderFee() == null) {
			order.setOrderFee(Order.DEFAULT_ORDER_FEE);
			logger.debug("Adding Fee to order: " + order);
		}
		
		Account account = accountService.findAccount(order.getAccountId());
		BigDecimal currentBalance = account.getBalance();


		if (order.getOrderType() == OrderType.BUY) {
			double amount = order.getQuantity()* order.getPrice().doubleValue()+ order.getOrderFee().doubleValue();			
			BigDecimal newBalance = currentBalance.subtract(new BigDecimal(amount));

			if (newBalance.compareTo(BigDecimal.ZERO) >= 0) {
				account.setBalance(newBalance);
				accountService.saveAccount(account);				

			} else {
				throw new OrderNotSavedException("No Sufficient Funds!!!");
			}
			
			
			
		} else {
			double amount = order.getQuantity()	* order.getPrice().doubleValue()- order.getOrderFee().doubleValue();			

				BigDecimal newBalance = currentBalance.add(new BigDecimal(amount));
				

				account.setBalance(newBalance);
				accountService.saveAccount(account);
		
			
			
			
		}
		
		logger.info(String.format("Account funds updated successfully for account: %s and new funds are: %s",
						order.getAccountId(), account.getBalance()));
		return repository.save(order);
	}
}

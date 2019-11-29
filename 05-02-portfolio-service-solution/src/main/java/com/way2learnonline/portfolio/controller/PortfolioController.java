package com.way2learnonline.portfolio.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.way2learnonline.portfolio.domain.Order;
import com.way2learnonline.portfolio.domain.Portfolio;
import com.way2learnonline.portfolio.service.PortfolioService;

@RestController
public class PortfolioController {
	private static final Logger logger = LoggerFactory
			.getLogger(PortfolioController.class);

	/**
	 * the service to delegate to.
	 */
	@Autowired
	private PortfolioService portFolioService;

	/**
	 * Retrieves the portfolio for the given account.
	 * @param accountId the account to retrieve the portfolio for.
	 * @return The portfolio with HTTP OK.
	 */
	@RequestMapping(value = "/portfolio/{id}", method = RequestMethod.GET)
	public ResponseEntity<Portfolio> getPortfolio(@PathVariable("id") final String accountId) {
		logger.debug("PortfolioController: Retrieving portfolio with user id:" + accountId);
		Portfolio folio = portFolioService.getPortfolio(accountId);
		logger.debug("PortfolioController: Retrieved portfolio:" + folio);
		return new ResponseEntity<Portfolio>(folio, getNoCacheHeaders(), HttpStatus.OK);
	}
	
	private HttpHeaders getNoCacheHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Cache-Control", "no-cache");
		return responseHeaders;
	}
	/**
	 * Adds an order to the portfolio of the given account.
	 * 
	 * @param accountId the account to add the order to.
	 * @param order The order to add.
	 * @param builder
	 * @return The order with HTTP CREATED or BAD REQUEST if it couldn't save.
	 */
	@RequestMapping(value = "/portfolio/{id}", method = RequestMethod.POST)
	public ResponseEntity<Order> addOrder(@PathVariable("id") final String accountId, @RequestBody final Order order, UriComponentsBuilder builder) {
		logger.debug("Adding Order: " + order);
		
		//TODO: can do a test to ensure accountId == order.getAccountId();
		
		Order savedOrder = portFolioService.addOrder(order);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(builder.path("/portfolio/{id}")
				.buildAndExpand(accountId).toUri());
		logger.debug("Order added: " + savedOrder);
		if (savedOrder != null && savedOrder.getOrderId() != null) {
			return new ResponseEntity<Order>(savedOrder, responseHeaders, HttpStatus.CREATED);
		} else {
			logger.warn("Order not saved: " + order);
			return new ResponseEntity<Order>(savedOrder, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

package com.way2learnonline.accounts.controller;

import java.math.BigDecimal;



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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.way2learnonline.accounts.domain.Account;
import com.way2learnonline.accounts.service.AccountService;


@RestController
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	
	@Autowired
	private AccountService service;


	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
	public ResponseEntity<Account> find(@PathVariable("id") final Integer id) {

		logger.info("AccountController.find: id=" + id);

		Account accountResponse = this.service.findAccount(id);
		return new ResponseEntity<Account>(accountResponse, getNoCacheHeaders(), HttpStatus.OK);

	}

	
	@RequestMapping(value = "/account/", method = RequestMethod.GET)
	public ResponseEntity<Account> findAccount(@RequestParam(value = "name") final String id) {

		logger.info("AccountController.findAccount: id=" + id);

		Account accountResponse = this.service.findAccount(id);
		return new ResponseEntity<Account>(accountResponse, getNoCacheHeaders(), HttpStatus.OK);

	}

	
	@RequestMapping(value = "/account", method = RequestMethod.POST,consumes={"application/json","application/xml"})
	public ResponseEntity<String> save(@RequestBody Account accountRequest, UriComponentsBuilder builder) {

		logger.debug("AccountController.save: userId=" + accountRequest.getUserid());

		Integer accountProfileId = this.service.saveAccount(accountRequest);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(builder.path("/account/{id}").buildAndExpand(accountProfileId).toUri());
		return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
	}

	/**
	 * REST call to decrease the balance in the account. Decreases the balance
	 * of the account if the new balance is not lower than zero. Returns HTTP OK
	 * and the new balance if the decrease was successful, or HTTP
	 * EXPECTATION_FAILED if the new balance would be negative and the
	 * old/current balance.
	 * 
	 * @param userId
	 *            The id of the account.
	 * @param amount
	 *            The amount to decrease the balance by.
	 * @return The new balance of the account with HTTP OK.
	 */
	@RequestMapping(value = "/accounts/{userId}/decreaseBalance/{amount}", method = RequestMethod.GET,produces={"application/json"})
	public ResponseEntity<Double> decreaseBalance(@PathVariable("userId") final String userId, @PathVariable("amount") final double amount) {

		logger.debug("AccountController.decreaseBalance: id='" + userId + "', amount='" + amount + "'");

		Account accountResponse = this.service.findAccount(userId);

		BigDecimal currentBalance = accountResponse.getBalance();

		BigDecimal newBalance = currentBalance.subtract(new BigDecimal(amount));

		if (newBalance.compareTo(BigDecimal.ZERO) >= 0) {
			accountResponse.setBalance(newBalance);
			this.service.saveAccount(accountResponse);
			return new ResponseEntity<Double>(accountResponse.getBalance().doubleValue(), getNoCacheHeaders(), HttpStatus.OK);

		} else {
			// no sufficient founds available
			return new ResponseEntity<Double>(accountResponse.getBalance().doubleValue(), getNoCacheHeaders(), HttpStatus.EXPECTATION_FAILED);
		}

	}

	/**
	 * REST call to increase the balance in the account. Increases the balance
	 * of the account if the amount is not negative. Returns HTTP OK and the new
	 * balance if the increase was successful, or HTTP EXPECTATION_FAILED if the
	 * amount given is negative.
	 * 
	 * @param userId
	 *            The id of the account.
	 * @param amount
	 *            The amount to increase the balance by.
	 * @return The new balance of the account with HTTP OK.
	 */
	@RequestMapping(value = "/accounts/{userId}/increaseBalance/{amount}", method = RequestMethod.GET,produces={"application/json"})
	public ResponseEntity<Double> increaseBalance(@PathVariable("userId") final String userId, @PathVariable("amount") final double amount) {

		logger.debug("AccountController.increaseBalance: id='" + userId + "', amount='" + amount + "'");

		Account accountResponse = this.service.findAccount(userId);

		BigDecimal currentBalance = accountResponse.getBalance();

		logger.debug("AccountController.increaseBalance: current balance='" + currentBalance + "'.");

		if (amount > 0) {

			BigDecimal newBalance = currentBalance.add(new BigDecimal(amount));
			logger.debug("AccountController.increaseBalance: new balance='" + newBalance + "'.");

			accountResponse.setBalance(newBalance);
			this.service.saveAccount(accountResponse);
			return new ResponseEntity<Double>(accountResponse.getBalance().doubleValue(), getNoCacheHeaders(), HttpStatus.OK);

		} else {
			// amount can not be negative for increaseBalance, please use
			// decreaseBalance
			return new ResponseEntity<Double>(accountResponse.getBalance().doubleValue(), getNoCacheHeaders(), HttpStatus.EXPECTATION_FAILED);
		}

	}

	private HttpHeaders getNoCacheHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Cache-Control", "no-cache");
		return responseHeaders;
	}
}

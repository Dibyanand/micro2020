package com.way2learnonline.service;

import java.util.Map;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.way2learnonline.domain.Account;
import com.way2learnonline.domain.AuthenticationRequest;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory
			.getLogger(UserService.class);
	

	@Autowired
	private AccountService accountService;

	
	public void createAccount(Account account) {
		logger.debug("Saving account with userId: " + account.getUserid());
		

		accountService.saveAccount(account);
		//String status = restTemplate.postForObject("http://" + accountsService + "/account/", account, String.class);
		//logger.info("Status from registering account for "+ account.getUserid()+ " is " + status);
	}
	
	public Map<String,Object> login(AuthenticationRequest request){
		logger.debug("logging in with userId:" + request.getUsername());
		
		
		
		Map<String,Object> result = accountService.login(request.getUsername(), request.getPassword());
		return result;
	}
	
	//TODO: change to /account/{user}
	public Account getAccount(String user) {
		logger.debug("Looking for account with userId: " + user);
		
		
		
	    Account account = accountService.findAccount(user);
	    logger.debug("Got Account: " + account);
	    return account;
	}
	
	public void logout(String user) {
		logger.debug("logging out account with userId: " + user);
		
		accountService.logout(user);
		
	   
	}
	
}

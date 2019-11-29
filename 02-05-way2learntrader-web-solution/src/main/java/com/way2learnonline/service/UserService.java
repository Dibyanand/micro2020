package com.way2learnonline.service;

import java.net.URI;
import java.util.List;
import java.util.Map;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Value("${pivotal.accountsService.name}")
	private String accountsService;
	
	public void createAccount(Account account) {
		logger.debug("Saving account with userId: " + account.getUserid());
		
		
		String status = restTemplate.postForObject(getAccountServiceURI()+ "/account/", account, String.class);
		logger.info("Status from registering account for "+ account.getUserid()+ " is " + status);
	}
	
	public Map<String,Object> login(AuthenticationRequest request){
		logger.debug("logging in with userId:" + request.getUsername());
		
	
		
		Map<String,Object> result = (Map<String, Object>) restTemplate.postForObject(getAccountServiceURI() + "/login/".toString(), request, Map.class);
		return result;
	}
	
	//TODO: change to /account/{user}
	public Account getAccount(String user) {
		logger.debug("Looking for account with userId: " + user);
		
	    Account account = restTemplate.getForObject(getAccountServiceURI()+ "/account/?name={user}", Account.class, user);
	    logger.debug("Got Account: " + account);
	    return account;
	}
	
	public void logout(String user) {
		logger.debug("logging out account with userId: " + user);
		
	
		
	    ResponseEntity<?> response = restTemplate.getForEntity(getAccountServiceURI()+ "/logout/{user}", String.class, user);
	    logger.debug("Logout response: " + response.getStatusCode());
	}
	
	public URI getAccountServiceURI(){
		List<ServiceInstance> instances=  discoveryClient.getInstances(accountsService);
		URI uri=instances.get(0).getUri();
		System.err.println("Account Service URI : "+uri);
		return uri;
	}
	
}

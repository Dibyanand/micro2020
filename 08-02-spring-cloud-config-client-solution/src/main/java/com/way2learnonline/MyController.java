package com.way2learnonline;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MyController {
	
	@Value("${test.message}")
	private String message;
	
	@RequestMapping("/msg")
	public String getMsg(){
		return "Hello "+message;
	}

}

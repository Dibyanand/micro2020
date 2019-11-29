package com.way2learnonline.portfolio.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolKey;

	public class MyHystrixCommand extends HystrixCommand<String> {
		
		MyCirctuitBreaker circtuitBreaker;
	
		public MyHystrixCommand(HystrixCommandGroupKey group, HystrixThreadPoolKey threadPool) {
			super(group, threadPool);		
			
		}		
	
		@Override
		protected String run() throws Exception {
			
			
			
			if(circtuitBreaker.isOpen()){
				// execute the fall back logic
			}else{
				// execute the actual logic
				//if actual logic is successful, do as below
				circuitBreaker.markSuccess();
			}
			return null;
		}
		
	
	}

	
	
package com.way2learnonline.portfolio.service;

import com.netflix.hystrix.HystrixCircuitBreaker;

	public class MyCirctuitBreaker implements HystrixCircuitBreaker {
	
		@Override
		public boolean allowRequest() {
			// TODO Auto-generated method stub
			return false;
		}
	
		@Override
		public boolean isOpen() {
			// TODO Auto-generated method stub
			return false;
		}
	
		@Override
		public void markSuccess() {
			// TODO Auto-generated method stub
	
		}

		@Override
		public boolean attemptExecution() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void markNonSuccess() {
			// TODO Auto-generated method stub
			
		}
	
	}

	
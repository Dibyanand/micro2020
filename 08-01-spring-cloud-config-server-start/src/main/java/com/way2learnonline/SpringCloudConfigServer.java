package com.way2learnonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


	@SpringBootApplication
	// TODO -2 Configure corresponding annotation to enable config server 
	//@EnableConfigServer
	public class SpringCloudConfigServer {
	
		public static void main(String[] args) {
			SpringApplication.run(SpringCloudConfigServer.class, args);
		}
	}
	
	
	

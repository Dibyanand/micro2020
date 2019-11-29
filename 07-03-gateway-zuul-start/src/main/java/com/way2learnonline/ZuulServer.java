package com.way2learnonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
//TODO -1 configure  annotation to enable zuul proxy

//@EnableZuulProxy
//TODO -2 configure annotation to enable this application as DiscoveryClient
//@EnableDiscoveryClient
public class ZuulServer {

	public static void main(String[] args) {
		SpringApplication.run(ZuulServer.class, args);
	}
}

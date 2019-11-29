package com.way2learnonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Way2LearnTrader {

	public static void main(String[] args) {
		SpringApplication.run(Way2LearnTrader.class, args);

	}

}

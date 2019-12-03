package com.luv2code.aopdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class TrafficFortuneService {

	public String getFortune() {
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return "Expect heavy traffic this morning";
	}

	public String getFortune(boolean tripWare) {
		
		if(tripWare) {
			throw new RuntimeException("HighWay is closed!!! (exception)");
		}
		
		return getFortune();
	}
	
}

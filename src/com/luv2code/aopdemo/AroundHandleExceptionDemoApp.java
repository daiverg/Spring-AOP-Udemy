package com.luv2code.aopdemo;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.service.TrafficFortuneService;


public class AroundHandleExceptionDemoApp {
	
	private static Logger myLogger = Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());

	public static void main(String[] args) {
		
//		read spring config java class
		AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(DemoConfig.class);
		
//		Retrieve bean from spring
		TrafficFortuneService theFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
//		simulate exception
		
		boolean tripWare = true;
		String data = theFortuneService.getFortune(tripWare);
		
		
		
		myLogger.info(data);
		
		
		
		
		myLogger.info("Finish");
//		close
		context.close();
	}

}

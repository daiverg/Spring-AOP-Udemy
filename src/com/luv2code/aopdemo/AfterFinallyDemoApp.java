package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;

public class AfterFinallyDemoApp {

	public static void main(String[] args) {
		
//		read spring config java class
		AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(DemoConfig.class);
		
//		Retrieve bean from spring
		AccountDAO theAccount = context.getBean("accountDAO", AccountDAO.class);
		
		List<Account> theAccounts = null;
		
		
		try {
//			add flag to simulate exception
			boolean tripWare = false; 
			theAccounts = theAccount.findAccounts(tripWare);
		} catch (Exception exc) {
			System.out.println("main program cach: " + exc);
		}
		
						
		System.out.println(theAccounts);
		
		
		
//		close
		context.close();
	}

}

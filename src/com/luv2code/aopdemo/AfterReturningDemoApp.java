package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;

public class AfterReturningDemoApp {

	public static void main(String[] args) {
		
//		read spring config java class
		AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(DemoConfig.class);
		
//		Retrieve bean from spring
		AccountDAO theAccount = context.getBean("accountDAO", AccountDAO.class);
		
		List<Account> theAccounts = theAccount.findAccounts();
		
		
		System.out.println("\n\nMain Program: AfterReturningDemoApp");
		System.out.println("--------------");
		
		System.out.println(theAccounts);
		
		
		
//		close
		context.close();
	}

}

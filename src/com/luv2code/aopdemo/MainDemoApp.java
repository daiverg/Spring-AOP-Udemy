package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		
//		read spring config java class
		AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(DemoConfig.class);
		
//		Retrieve bean from spring
		AccountDAO theAccount = context.getBean("accountDAO", AccountDAO.class);
		
//		call method
		theAccount.addAccount();

		
//		close
		context.close();
		

	}

}

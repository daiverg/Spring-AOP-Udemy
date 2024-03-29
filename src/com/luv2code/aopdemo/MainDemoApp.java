package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		
//		read spring config java class
		AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(DemoConfig.class);
		
//		Retrieve bean from spring
		AccountDAO theAccount = context.getBean("accountDAO", AccountDAO.class);
		
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		Account myAccount = new Account();
		myAccount.setName("Vasja");
		myAccount.setLevel("Platinum");
		
//		call method
		theAccount.addAccount(myAccount, true);
		theAccount.doWork();
		
		theAccount.setName("foobar");
		theAccount.setServiceCode("silver");
		
		String name = theAccount.getName();
		String code = theAccount.getServiceCode();
		
		
		theMembershipDAO.addSillyMember();
		theMembershipDAO.goToSleep();
		
		
//		close
		context.close();
		

	}

}

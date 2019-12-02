package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

	public boolean addSillyMember() {
		System.out.println("MemberShip class add account");
		
		return true;
	}
	
	public void goToSleep() {
		
		System.out.println("goTo sleep membership");
	}
	
}

package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

//	add all related advises for logging
	
//	@beforeAdvice
	
//	@Before("execution(public void add*())")
//	@Before("execution(* *())")
//	@Before("execution(* add*(com.luv2code.aopdemo.Account, ..))")
	@Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	public void beforeAddAccountAdvice() {
		
		System.out.println("\n======>>>> @before advice");
	}
	
}

package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
	
		System.out.println("\n======>>>> @before advice order 2");
	
	
//	display method signature
	MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
	
	System.out.println("method: " + methodSig);
	
//	display method arguments
	
	Object[] args = theJoinPoint.getArgs();
	
	for (Object tempArg : args) {
		
		System.out.println(tempArg);
		
		if (tempArg instanceof Account) {
//			downcast and print Account specific stuff
			Account theAccount = (Account) tempArg;
			System.out.println("(DownCast) account name: " + theAccount.getName());
			System.out.println("account level: " + theAccount.getLevel());
			
		}
	}
	
	}
}

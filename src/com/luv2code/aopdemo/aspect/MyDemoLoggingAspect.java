package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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
	
//	add a new advice @AfterReturning
	
	@AfterReturning(pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result")
		public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
		
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n==========> executing @AfterReturning " + method);
		
		System.out.println("result is:" + result);
		
//		let's post-process the data .... let's modify it
//		convert to uppercase
		convertAccountNamesToUpperCase(result);
		
		
	}

	private void convertAccountNamesToUpperCase(List<Account> result) {
////	my advice
//		for(Account tempAccount : result) {
//			tempAccount.setName(tempAccount.getName().toUpperCase());
//		}
//		teacher advice
		for(Account tempAccount : result) {
			String theUpperName = tempAccount.getName().toUpperCase();
			tempAccount.setName(theUpperName);
		}
	
}

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

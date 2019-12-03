package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;
import com.luv2code.aopdemo.AroundWithLoggerDemoApp;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint theProceedJoinPoint) throws Throwable {
//		calculate duration time
		String method = theProceedJoinPoint.getSignature().toShortString();
		myLogger.info("\n==========> executing @Around: " + method);
		
		long begin = System.currentTimeMillis();
		
		Object result = theProceedJoinPoint.proceed();
		
		long end = System.currentTimeMillis();
		
		long duration = end - begin;
		
		myLogger.info("\n==========> Duration: " + duration / 1000.0 + " sec.");
		
		return result;
		
	}
	
	
//	after finnaly
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n==========> executing @After (finally) : " + method);
	}
	
	
//	add a new advice @AfterException
	
	@AfterThrowing(pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing="theExc"
			)
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint , Throwable theExc) {
		
//		print wich method advising
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n==========> executing @AfterException " + method + "\n Exception: " + theExc);
		
	}
	
	
	
//	add a new advice @AfterReturning
	
	@AfterReturning(pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result")
		public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
		
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n==========> executing @AfterReturning " + method);
		
		myLogger.info("result is:" + result);
		
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
	
		myLogger.info("\n======>>>> @before advice order 2");
	
	
//	display method signature
	MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
	
	myLogger.info("method: " + methodSig);
	
//	display method arguments
	
	Object[] args = theJoinPoint.getArgs();
	
	for (Object tempArg : args) {
		
		myLogger.info(tempArg.toString());
		
		if (tempArg instanceof Account) {
//			downcast and print Account specific stuff
			Account theAccount = (Account) tempArg;
			myLogger.info("(DownCast) account name: " + theAccount.getName());
			myLogger.info("account level: " + theAccount.getLevel());
			
		}
	}
	
	}
}

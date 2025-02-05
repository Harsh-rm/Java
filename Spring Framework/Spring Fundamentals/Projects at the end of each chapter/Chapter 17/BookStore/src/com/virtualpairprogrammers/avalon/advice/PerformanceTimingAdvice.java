package com.virtualpairprogrammers.avalon.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PerformanceTimingAdvice 
{
	@Pointcut("execution (* com.virtualpairprogrammers.avalon.services.*.*(..) ) ")
	public void allServiceMethods() {}
	
	@Around("allServiceMethods()")
	public Object performTimingMeasurement(ProceedingJoinPoint method) throws Throwable 
	{
		// before
		long startTime = System.nanoTime();			
		
		try
		{
			// proceed to target
			Object returnValue = method.proceed();

			// TODO Auto-generated method stub
			return returnValue;			
		}
		finally
		{			
			// after
			long endTime = System.nanoTime();
			
			long timeTaken = endTime - startTime;
			
			System.out.println("The method " + method.getSignature().getName() + " took " + timeTaken + " nanoseconds");
		}
		
		
	}
	
	@Before("allServiceMethods()")
	public void beforeAdviceTesting(JoinPoint jp)
	{
		System.out.println("Now entering a method...." + jp.getSignature().getName());
	}

}

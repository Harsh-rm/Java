package com.virtualpairprogrammers.avalon.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class PerformanceTimingAdvice 
{
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
	
	public void beforeAdviceTesting(JoinPoint jp)
	{
		System.out.println("Now entering a method...." + jp.getSignature().getName());
	}

}

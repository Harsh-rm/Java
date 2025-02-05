package com.virtualpairprogrammers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import com.virtualpairprogrammers.beans.ClassA;
import com.virtualpairprogrammers.beans.ClassB;

@Configuration
public class ApplicationConfiguration 
{
	/*
	<bean id="beanA" class="com.virtualpairprogrammers.beans.ClassA">
		<property name="dependency" ref="beanB"/>
	</bean>
	 */
	
	@Bean(initMethod="createdBeanA")
	@Scope("prototype")
	public ClassA beanA()
	{
		System.out.println("CALLED THE CREATION OF BEAN A");
		ClassA beanA = new ClassA();
		beanA.setDependency(beanB());
		return beanA;
	}
	
	/*
	<bean id="beanB" class="com.virtualpairprogrammers.beans.ClassB">
		<property name="value" value="Some amazing value"/>
	</bean>
	*/
	@Bean(destroyMethod="destroyedBeanB")
	// SINGLETON
	public ClassB beanB()
	{
		System.out.println("CALLED THE CREATION OF BEAN B");
		ClassB beanB = new ClassB();
		beanB.setValue("A second amazing value");
		return beanB;
	}
	
}

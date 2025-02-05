package com.virtualpairprogrammers.beans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class ClassA {
	private ClassB dependency;
	
	public ClassB getDependency() {
		return dependency;
	}

	public void setDependency(ClassB dependency) {
		this.dependency = dependency;
	}

	public void businessMethod()
	{
		System.out.println(dependency.getValue());
	}
	
	public void createdBeanA()
	{
		System.out.println("created bean a");
	}
}

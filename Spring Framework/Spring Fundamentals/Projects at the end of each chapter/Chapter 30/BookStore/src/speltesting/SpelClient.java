package speltesting;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpelClient {

	public static void main(String[] args) 
	{
		ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("application.xml");
		
		SimpleBeanB bean = factory.getBean(SimpleBeanB.class);
		
		System.out.println(bean.getRandomValue());
		
		factory.close();
	}

}

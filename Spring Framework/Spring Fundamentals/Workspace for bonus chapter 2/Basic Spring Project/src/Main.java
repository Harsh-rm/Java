import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.virtualpairprogrammers.beans.ClassA;
import com.virtualpairprogrammers.config.ApplicationConfiguration;

public class Main 
{
	public static void main(String[] args)
	{
		AnnotationConfigApplicationContext container = 
				new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		
		ClassA bean = container.getBean(ClassA.class);
		bean.businessMethod();
		
		ClassA secondBean = container.getBean(ClassA.class);
		secondBean.businessMethod();
		
		container.close();
	}
}

package speltesting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SimpleBeanA {
	
	@Value("SOME IMPORTANT BUSINESS VALUE")
	private String simpleValue;

	public String getSimpleValue() {
		System.out.println("Calling the getter");
		return simpleValue;
	}

	
	public void setSimpleValue(String simpleValue) {
		this.simpleValue = simpleValue;
	}
	
	
}

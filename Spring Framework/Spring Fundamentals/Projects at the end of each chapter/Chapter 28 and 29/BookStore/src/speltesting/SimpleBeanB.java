package speltesting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SimpleBeanB 
{
	@Value("#{simpleBeanA.simpleValue}")
	private String secondValue;
	
	@Value("#{ (new Double(T(java.lang.Math).random() * 10)).intValue() + 1 }")
	private int randomValue;

	public int getRandomValue() {
		return randomValue;
	}

	public void setRandomValue(int randomValue) {
		this.randomValue = randomValue;
	}

	public String getSecondValue() {
		return secondValue;
	}

	public void setSecondValue(String secondValue) {
		this.secondValue = secondValue;
	}
	
	
}

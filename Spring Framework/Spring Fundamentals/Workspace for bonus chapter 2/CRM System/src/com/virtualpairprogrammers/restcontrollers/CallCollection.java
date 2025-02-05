package com.virtualpairprogrammers.restcontrollers;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.virtualpairprogrammers.domain.Call;

@XmlRootElement(name="calls")
public class CallCollection 
{
	private List<Call> calls;
	
	public CallCollection() {}
	
	public CallCollection(List<Call> calls)
	{
		this.calls = calls;
	}

	@XmlElement(name="call")
	public List<Call> getCalls() {
		return calls;
	}

	public void setCalls(List<Call> calls) {
		this.calls = calls;
	}
	
	
}

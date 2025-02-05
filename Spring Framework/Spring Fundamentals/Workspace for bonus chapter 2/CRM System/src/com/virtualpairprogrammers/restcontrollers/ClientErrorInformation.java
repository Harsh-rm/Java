package com.virtualpairprogrammers.restcontrollers;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClientErrorInformation 
{
	private String message;
	private String uri;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ClientErrorInformation(String message, String uri) {
		super();
		this.message = message;
		this.uri = uri;
	}
	
	public ClientErrorInformation() {}
	
	
}

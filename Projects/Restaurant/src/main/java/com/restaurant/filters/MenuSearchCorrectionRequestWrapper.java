package com.restaurant.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MenuSearchCorrectionRequestWrapper extends HttpServletRequestWrapper {
	
	private String newSearchTerm;

	public MenuSearchCorrectionRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	public void setNewSearchTerm(String newSearchTerm) {
		this.newSearchTerm = newSearchTerm;
	}
	
	@Override
	public String getParameter(String key) {
		if (key.equals("searchTerm")) {
			return newSearchTerm;
		} else {
			return super.getParameter(key);
		}
	}

}

package com.model;

import java.io.Serializable;

public class Request implements Serializable {

	private static final long serialVersionUID = 1L;
	private String filter;

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	
	
}

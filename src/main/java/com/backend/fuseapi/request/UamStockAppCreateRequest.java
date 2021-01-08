package com.backend.fuseapi.request;

import java.io.Serializable;

public class UamStockAppCreateRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	
	private String title;
	
	public UamStockAppCreateRequest() {
		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
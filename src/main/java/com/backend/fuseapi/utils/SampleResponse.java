package com.backend.fuseapi.utils;

import java.io.Serializable;

public class SampleResponse implements Serializable {

	private String username;
	
	private String title;
	
	public SampleResponse() {
		
	}
	
	public SampleResponse(String username, String title) {
		this.username = username;
		this.title = title;
	}
	
	public String getUsername() {
		return this.username;
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

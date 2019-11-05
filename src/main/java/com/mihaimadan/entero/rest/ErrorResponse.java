package com.mihaimadan.entero.rest;

import java.time.LocalTime;

public class ErrorResponse {
	
	private int status;
	private String message;
	private LocalTime time;
	
	public ErrorResponse(int status, String message, LocalTime time) {
		
		this.status = status;
		this.message = message;
		this.time = time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	
	

}

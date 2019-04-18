package com.rabobank.exception;

public class ErrorResponse {

	private String errorCode;
	private String description;

	public String getError() {
		return errorCode;
	}

	public void setError(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

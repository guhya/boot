package net.guhya.boot.exception;

import java.time.LocalDateTime;

public class ErrorJson {
	
	private int status;
	private String error;
	private String message;
	LocalDateTime timestamp;
	
	public ErrorJson() {
	}

	public ErrorJson(int status, String error, String message, LocalDateTime timestamp) {
		this.status = status;
		this.error = error;
		this.message = message;
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

}

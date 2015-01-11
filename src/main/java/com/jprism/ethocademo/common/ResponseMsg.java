package com.jprism.ethocademo.common;

public class ResponseMsg {
	
	private String message;
	private boolean status;
	private boolean success;

	
	public ResponseMsg() {
		
	}
	
	@Override
	public String toString() {
		return "ResponseMessage [message=" + message + ", status=" + status
				+ "]";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
	

}

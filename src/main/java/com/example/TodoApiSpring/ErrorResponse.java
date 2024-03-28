package com.example.TodoApiSpring;

import org.springframework.http.HttpStatus;

public class ErrorResponse{
	private String data;
	private boolean success;
	private String error;
	private String message;

	public ErrorResponse(String data, boolean success, String error, String message) {
		this.data = data;
		this.success = success;
		this.error = error;
		this.message = message;
	}

	public void setData(String data){
		this.data = data;
	}

	public String getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setError(String error){
		this.error = error;
	}

	public String getError(){
		return error;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"ErrorResponse{" + 
			"data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}

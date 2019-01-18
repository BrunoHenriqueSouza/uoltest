package br.com.api.clientRegister.vo;

import org.springframework.http.HttpStatus;

public class ResponseClientResourceVO {

	private HttpStatus errorCode;
	private String message;
	private ClientVO client;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}

	public ClientVO getClient() {
		return client;
	}

	public void setClient(ClientVO client) {
		this.client = client;
	}
	
}

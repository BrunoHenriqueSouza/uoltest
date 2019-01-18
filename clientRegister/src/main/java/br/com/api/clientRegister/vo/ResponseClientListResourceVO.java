package br.com.api.clientRegister.vo;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ResponseClientListResourceVO {

	private HttpStatus errorCode;
	private String message;
	private List<ClientVO> clients;

	
	public HttpStatus getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ClientVO> getClients() {
		return clients;
	}

	public void setClients(List<ClientVO> clients) {
		this.clients = clients;
	}
	
}

package br.com.api.clientRegister.integration.ipVigilant.responseVO;

public class IpVigiliantIntegrationErrorVO {

	private String code;
	private String message;
	private int numberErrors;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getNumberErrors() {
		return numberErrors;
	}

	public void setNumberErrors(int numberErrors) {
		this.numberErrors = numberErrors;
	}
	
}

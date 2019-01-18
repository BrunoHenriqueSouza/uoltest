package br.com.api.clientRegister.integration.ipVigilant.responseVO;

import java.util.List;

public class IpVigilantIntegrationResponseVO {

	private String status;
	private IpVigilantIntegrationDataVO data;
	private List<IpVigiliantIntegrationErrorVO> errors;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public IpVigilantIntegrationDataVO getData() {
		return data;
	}

	public void setData(IpVigilantIntegrationDataVO data) {
		this.data = data;
	}

	public List<IpVigiliantIntegrationErrorVO> getErrors() {
		return errors;
	}

	public void setErrors(List<IpVigiliantIntegrationErrorVO> errors) {
		this.errors = errors;
	}
	
}

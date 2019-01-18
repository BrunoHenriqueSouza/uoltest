package br.com.api.clientRegister.integration.ipVigilant;

import br.com.api.clientRegister.vo.GeoLocationVO;

public interface IpVigilanteIntegration {

	GeoLocationVO callServiceToGetLocationByIp(String ip);
	
}

package br.com.api.clientRegister.integration.ipVigilant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.api.clientRegister.integration.ipVigilant.responseVO.IpVigilantIntegrationResponseVO;
import br.com.api.clientRegister.vo.GeoLocationVO;

@Component
public class IpVigilanteIntegrationImpl implements IpVigilanteIntegration{
	
	private static final String SERVICE_URL = "https://ipvigilante.com/json/";
	private static final String STATUS_SUCCESS = "success";
	
	private GeoLocationVO geoLocationVO;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public GeoLocationVO callServiceToGetLocationByIp(String ip) {
		
		try {
			IpVigilantIntegrationResponseVO response = restTemplate.getForObject(SERVICE_URL + ip, IpVigilantIntegrationResponseVO.class);
			
			if(response.getStatus().equals(STATUS_SUCCESS)) {
				geoLocationVO = new GeoLocationVO();
				geoLocationVO.setCity_name(response.getData().getCity_name());
				geoLocationVO.setContinent_name(response.getData().getContinent_name());
				geoLocationVO.setCountry_name(response.getData().getCountry_name());
				geoLocationVO.setLatitude(response.getData().getLatitude());
				geoLocationVO.setLongitude(response.getData().getLongitude());
				geoLocationVO.setSubdivision_1_name(response.getData().getSubdivision_1_name());
				geoLocationVO.setSubdivision_2_name(response.getData().getSubdivision_2_name());
			}
		}catch(HttpClientErrorException e) {
			e.printStackTrace();
		}
		
		return geoLocationVO;
	}
	
	public static void main(String[] args) {
		IpVigilanteIntegrationImpl teste = new IpVigilanteIntegrationImpl();
		
		teste.callServiceToGetLocationByIp("8.8.8.a");
	}
	
}

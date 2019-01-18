package br.com.api.clientRegister.ipVigilantTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;

import br.com.api.clientRegister.integration.ipVigilant.IpVigilanteIntegrationImpl;
import br.com.api.clientRegister.integration.ipVigilant.responseVO.IpVigilantIntegrationDataVO;
import br.com.api.clientRegister.integration.ipVigilant.responseVO.IpVigilantIntegrationResponseVO;
import br.com.api.clientRegister.vo.GeoLocationVO;

@RunWith(MockitoJUnitRunner.class)
public class IpVigilantIntegrationTest {
	
	@InjectMocks
	private IpVigilanteIntegrationImpl ipVigilantIntegration;
	
	@Mock
	private RestTemplate restTemplate;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void callServiceToTakeThePositionByValidIpAndValidateIfReturnIsNotNull() {
		String ip = "8.8.8.8";
		GeoLocationVO geoLocationVO = null;
		IpVigilantIntegrationResponseVO ipVigilantIntegrationResponseVO = buildIpVigilantIntegrationResponseVO();
		
		Mockito.when(restTemplate.getForObject("https://ipvigilante.com/json/" + ip, IpVigilantIntegrationResponseVO.class))
			   .thenReturn(ipVigilantIntegrationResponseVO);
		
		geoLocationVO = ipVigilantIntegration.callServiceToGetLocationByIp(ip);


		
		assertNotNull(geoLocationVO);
	}
	
	@Test
	public void callServiceToTakeThePositionByValidIpAndValidateTheReturn() {
		String ip = "8.8.8.8";
		GeoLocationVO geoLocationVO = null;
		IpVigilantIntegrationResponseVO ipVigilantIntegrationResponseVO = buildIpVigilantIntegrationResponseVO();
		
		Mockito.when(restTemplate.getForObject("https://ipvigilante.com/json/" + ip, IpVigilantIntegrationResponseVO.class))
			   .thenReturn(ipVigilantIntegrationResponseVO);
		
		geoLocationVO = ipVigilantIntegration.callServiceToGetLocationByIp(ip);

		assertEquals(geoLocationVO.getCity_name(), "Mountain View");
		assertEquals(geoLocationVO.getContinent_name(), "North America");
		assertEquals(geoLocationVO.getCountry_name(), "United States");
		assertEquals(geoLocationVO.getLatitude(), "37.38600");
		assertEquals(geoLocationVO.getLongitude(), "-122.08380");
	}
	
	@Test
	public void callServiceToTakeThePositionByInvalidIpAndValidateIfTheObjectReturnedIsNull() throws JsonParseException {
		String invalidIp = "8.8.8.a";
		GeoLocationVO geoLocationVO = null;
		
		Mockito.when(restTemplate.getForObject("https://ipvigilante.com/json/" + invalidIp, IpVigilantIntegrationResponseVO.class))
			   .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		
		geoLocationVO = ipVigilantIntegration.callServiceToGetLocationByIp("8.8.8.a");
		
		assertNull(geoLocationVO);
	}
	
	private IpVigilantIntegrationResponseVO buildIpVigilantIntegrationResponseVO() {
		IpVigilantIntegrationResponseVO ipVigilantIntegrationResponseVO = new IpVigilantIntegrationResponseVO();
		IpVigilantIntegrationDataVO ipVigilantIntegrationDataVO = new IpVigilantIntegrationDataVO();
		
		ipVigilantIntegrationDataVO.setLatitude("37.38600");
		ipVigilantIntegrationDataVO.setLongitude("-122.08380");
		ipVigilantIntegrationDataVO.setCity_name("Mountain View");
		ipVigilantIntegrationDataVO.setContinent_name("North America");
		ipVigilantIntegrationDataVO.setCountry_name("United States");
		ipVigilantIntegrationDataVO.setSubdivision_1_name("California");
		ipVigilantIntegrationDataVO.setSubdivision_2_name(null);
		
		ipVigilantIntegrationResponseVO.setData(ipVigilantIntegrationDataVO);
		ipVigilantIntegrationResponseVO.setErrors(null);
		ipVigilantIntegrationResponseVO.setStatus("success");
		
		return ipVigilantIntegrationResponseVO;
	}
}

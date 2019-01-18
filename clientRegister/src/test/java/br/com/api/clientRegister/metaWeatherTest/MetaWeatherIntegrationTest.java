package br.com.api.clientRegister.metaWeatherTest;

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
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.api.clientRegister.integration.metaWeather.MetaWeatherIntegrationImpl;
import br.com.api.clientRegister.integration.metaWeather.responseVO.MetaWeatherConsolidatedWeatherResponseVO;
import br.com.api.clientRegister.integration.metaWeather.responseVO.MetaWeatherLocationResponseVO;
import br.com.api.clientRegister.integration.metaWeather.responseVO.MetaWeatherResponseVO;
import br.com.api.clientRegister.vo.WeatherVO;

@RunWith(MockitoJUnitRunner.class)
public class MetaWeatherIntegrationTest {

	private String metaWeatherLocationService = "https://www.metaweather.com/api/location/search/?lattlong=37.38600,-122.08380"; 
	private String metaWeatherLocationServiceWithError = "https://www.metaweather.com/api/location/search/?lattlong=37.3860a,-122.0838b";
	private String metaWeatherService = "https://www.metaweather.com/api/location/2455920/";
	
	@InjectMocks
	private MetaWeatherIntegrationImpl metaWeatherIntegration;
	
	@Mock
	private RestTemplate restTemplate;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void callServiceWithValidLocationAndValidateIfTheReturnIsNotNull() {
		MetaWeatherLocationResponseVO[] metaWeatherLocationResponse = buildMetaWeatherLocationService();
		MetaWeatherResponseVO metaWeatherResponseVO = buildMetaWeatherResponseVO();
		Mockito.when(restTemplate.getForObject(metaWeatherLocationService,MetaWeatherLocationResponseVO[].class))
			   .thenReturn(metaWeatherLocationResponse);
		Mockito.when(restTemplate.getForObject("https://www.metaweather.com/api/location/2455920/", MetaWeatherResponseVO.class))
			   .thenReturn(metaWeatherResponseVO);
		
		WeatherVO weatherVO = metaWeatherIntegration.callMetaWeather("37.38600", "-122.08380");
		
		assertNotNull(weatherVO);
	}

	@Test
	public void callServiceWithValidLocationAndValidateTheReturn() {
		MetaWeatherLocationResponseVO[] metaWeatherLocationResponse = buildMetaWeatherLocationService();
		MetaWeatherResponseVO metaWeatherResponseVO = buildMetaWeatherResponseVO();
		Mockito.when(restTemplate.getForObject(metaWeatherLocationService,MetaWeatherLocationResponseVO[].class))
			   .thenReturn(metaWeatherLocationResponse);
		Mockito.when(restTemplate.getForObject(metaWeatherService, MetaWeatherResponseVO.class))
			   .thenReturn(metaWeatherResponseVO);
		
		WeatherVO weatherVO = metaWeatherIntegration.callMetaWeather("37.38600", "-122.08380");
		
		assertNotNull(weatherVO.getMaxTemp());
		assertNotNull(weatherVO.getMinTemp());
	}
	
	@Test
	public void callServiceWithInvalidLocationAndValidateThatObjectReturnedIsNull() {
		Mockito.when(restTemplate.getForObject(
						metaWeatherLocationServiceWithError,
						MetaWeatherLocationResponseVO[].class))
			   .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
		
		WeatherVO weatherVO = metaWeatherIntegration.callMetaWeather("37.3860a", "-122.0838b");
		
		assertNull(weatherVO);
	}
	
	private MetaWeatherLocationResponseVO[] buildMetaWeatherLocationService() {
		MetaWeatherLocationResponseVO[] metaWeatherLocationResponseArray = new MetaWeatherLocationResponseVO[1];
		MetaWeatherLocationResponseVO metaWeatherLocationResponseVO = new MetaWeatherLocationResponseVO();
		
		metaWeatherLocationResponseVO.setLatt_long("37.39999,-122.079552");
		metaWeatherLocationResponseVO.setLocation_type("City");
		metaWeatherLocationResponseVO.setTitle("Mountain View");
		metaWeatherLocationResponseVO.setWoeid(2455920);
		
		metaWeatherLocationResponseArray[0] = metaWeatherLocationResponseVO;
		
		return metaWeatherLocationResponseArray;
	}
	
	private MetaWeatherResponseVO buildMetaWeatherResponseVO() {
		MetaWeatherResponseVO metaWeatherResponseVO = new MetaWeatherResponseVO();
		MetaWeatherConsolidatedWeatherResponseVO[] consolidated_weather = new MetaWeatherConsolidatedWeatherResponseVO[1];
		MetaWeatherConsolidatedWeatherResponseVO metaWeatherConsolidatedWeatherResponseVO = new MetaWeatherConsolidatedWeatherResponseVO();
		
		metaWeatherConsolidatedWeatherResponseVO.setApplicable_date("2019-01-17");
		metaWeatherConsolidatedWeatherResponseVO.setMax_temp(15.166666666666666);
		metaWeatherConsolidatedWeatherResponseVO.setMin_temp(8.963333333333333);
		metaWeatherConsolidatedWeatherResponseVO.setWeather_state_name("Light Rain");
		
		consolidated_weather[0] = metaWeatherConsolidatedWeatherResponseVO;

		metaWeatherResponseVO.setConsolidated_weather(consolidated_weather);
		metaWeatherResponseVO.setTitle("Mountain View");
		metaWeatherResponseVO.setWoeid(String.valueOf(2455920));
		
		return metaWeatherResponseVO;
	}

}

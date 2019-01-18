package br.com.api.clientRegister.integration.metaWeather;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.api.clientRegister.integration.metaWeather.responseVO.MetaWeatherConsolidatedWeatherResponseVO;
import br.com.api.clientRegister.integration.metaWeather.responseVO.MetaWeatherLocationResponseVO;
import br.com.api.clientRegister.integration.metaWeather.responseVO.MetaWeatherResponseVO;
import br.com.api.clientRegister.vo.WeatherVO;

@Component
public class MetaWeatherIntegrationImpl implements MetaWeatherIntegration{

	private static Logger logger = LogManager.getLogger(MetaWeatherIntegrationImpl.class);
	
	private static final String SEARCH_LOCATION_BY_LATLONG_SERVICE = "https://www.metaweather.com/api/location/search/?lattlong=";
	private static final String GET_WEATHER_BY_WOEID_SERVICE = "https://www.metaweather.com/api/location/{woeid}/";
	private static final String KEY_WOEID = "{woeid}";
	
	private WeatherVO weatherVO;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public WeatherVO callMetaWeather(String latitude, String longitude) {
		try {
			MetaWeatherLocationResponseVO[] locationResponseVO = getLocationsByLatLong(latitude, longitude);
			
			if(locationResponseVO != null && locationResponseVO.length > 0) {
				for(MetaWeatherLocationResponseVO location : locationResponseVO) {
					MetaWeatherResponseVO weatherResponseVO = getWeatherByWoeid(location.getWoeid());
					
					weatherVO = getWeatherOfThisDate(weatherResponseVO);
					
					if(weatherVO != null) {
						break;
					}
				}
			}
		}catch(HttpServerErrorException e) {
			StringBuilder error = new StringBuilder("Erro ao recuperar os dados de clima para a localizacao: \n")
									  .append("lat -> ").append(latitude).append("\n")
									  .append("long -> ").append(longitude).append("\n")
									  .append(e);
									  
			logger.error(error.toString());
		}
		return weatherVO;
	}

	private WeatherVO getWeatherOfThisDate(MetaWeatherResponseVO weatherResponseVO) {
		WeatherVO weatherByResponse = null;
		
		for(MetaWeatherConsolidatedWeatherResponseVO consolidatedWeather : weatherResponseVO.getConsolidated_weather()) {
			try {
				if(isThisDay(consolidatedWeather.getApplicable_date())){
					weatherByResponse = new WeatherVO();
					weatherByResponse.setMaxTemp(String.valueOf(consolidatedWeather.getMax_temp()));
					weatherByResponse.setMinTemp(String.valueOf(consolidatedWeather.getMin_temp()));
				}
				
				if(weatherByResponse != null) {
					break;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return weatherByResponse;
	}

	private boolean isThisDay(String applicable_date) throws ParseException {
		boolean isThisDay = false;
		LocalDate applicableDateConverted = getDateByString(applicable_date);
		
		if(LocalDate.now().equals(applicableDateConverted)) {
			isThisDay = true;
		}
		
		return isThisDay;
	}

	private LocalDate getDateByString(String applicable_date) throws ParseException {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyy/MM/dd");
		LocalDate dateFormated = LocalDate.parse(applicable_date.replace("-", "/"), format);
		
		return dateFormated;
	}

	private MetaWeatherResponseVO getWeatherByWoeid(int woeid) {
		MetaWeatherResponseVO weatherResponseVO = restTemplate.getForObject(getWeatherUrlByWoeid(woeid), 
				MetaWeatherResponseVO.class);
		
		return weatherResponseVO;
	}

	private String getWeatherUrlByWoeid(int woeid) {
		return GET_WEATHER_BY_WOEID_SERVICE.replace(KEY_WOEID, String.valueOf(woeid));
	}

	private MetaWeatherLocationResponseVO[] getLocationsByLatLong(String latitude, String longitude) {		
		MetaWeatherLocationResponseVO[] locationResponseVO = restTemplate.getForObject(
																getServiceLocationUrl(latitude, longitude), 
																MetaWeatherLocationResponseVO[].class);
		
		return locationResponseVO;
	}

	private String getServiceLocationUrl(String latitude, String longitude) {
		StringBuilder url = new StringBuilder(SEARCH_LOCATION_BY_LATLONG_SERVICE)
								.append(latitude)
								.append(",")
								.append(longitude);
		return url.toString();
	}
	
}
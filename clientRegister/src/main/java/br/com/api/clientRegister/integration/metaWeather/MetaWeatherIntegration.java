package br.com.api.clientRegister.integration.metaWeather;

import br.com.api.clientRegister.vo.WeatherVO;

public interface MetaWeatherIntegration {

	WeatherVO callMetaWeather(String latitude, String longitude);
}

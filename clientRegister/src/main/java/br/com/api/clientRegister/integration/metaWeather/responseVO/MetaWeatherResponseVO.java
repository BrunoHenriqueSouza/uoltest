package br.com.api.clientRegister.integration.metaWeather.responseVO;

public class MetaWeatherResponseVO {

	private String title;
	private String woeid;
	private MetaWeatherConsolidatedWeatherResponseVO[] consolidated_weather;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWoeid() {
		return woeid;
	}
	public void setWoeid(String woeid) {
		this.woeid = woeid;
	}
	public MetaWeatherConsolidatedWeatherResponseVO[] getConsolidated_weather() {
		return consolidated_weather;
	}
	public void setConsolidated_weather(MetaWeatherConsolidatedWeatherResponseVO[] consolidated_weather) {
		this.consolidated_weather = consolidated_weather;
	}
	
}

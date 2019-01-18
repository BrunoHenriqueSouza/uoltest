package br.com.api.clientRegister.integration.metaWeather.responseVO;

public class MetaWeatherConsolidatedWeatherResponseVO {

	private long id;
	private String weather_state_name;
	private String applicable_date;
	private double min_temp;
	private double max_temp;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getWeather_state_name() {
		return weather_state_name;
	}
	public void setWeather_state_name(String weather_state_name) {
		this.weather_state_name = weather_state_name;
	}
	public String getApplicable_date() {
		return applicable_date;
	}
	public void setApplicable_date(String applicable_date) {
		this.applicable_date = applicable_date;
	}
	public double getMin_temp() {
		return min_temp;
	}
	public void setMin_temp(double min_temp) {
		this.min_temp = min_temp;
	}
	public double getMax_temp() {
		return max_temp;
	}
	public void setMax_temp(double max_temp) {
		this.max_temp = max_temp;
	}
	
}

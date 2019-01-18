package br.com.api.clientRegister.vo;

public class WeatherVO {

	private String minTemp;
	private String maxTemp;

	public String getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(String minTemp) {
		this.minTemp = minTemp;
	}

	public String getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(String maxTemp) {
		this.maxTemp = maxTemp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maxTemp == null) ? 0 : maxTemp.hashCode());
		result = prime * result + ((minTemp == null) ? 0 : minTemp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeatherVO other = (WeatherVO) obj;
		if (maxTemp == null) {
			if (other.maxTemp != null)
				return false;
		} else if (!maxTemp.equals(other.maxTemp))
			return false;
		if (minTemp == null) {
			if (other.minTemp != null)
				return false;
		} else if (!minTemp.equals(other.minTemp))
			return false;
		return true;
	}
	
}

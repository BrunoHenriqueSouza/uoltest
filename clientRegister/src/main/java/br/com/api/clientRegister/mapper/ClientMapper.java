package br.com.api.clientRegister.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.api.clientRegister.entity.ClientEntity;
import br.com.api.clientRegister.entity.WeatherEntity;
import br.com.api.clientRegister.vo.ClientVO;
import br.com.api.clientRegister.vo.WeatherVO;

/**
 * 
 * @author brunosouza
 *
**/

@Component
public class ClientMapper {
	
	public ClientEntity mapperClientVoToClienteEntity(ClientVO clientVO) {
		ClientEntity clientEntity = new ClientEntity();
		
		if(clientVO.getClientId() != null) {
			clientEntity.setClientId(clientVO.getClientId());
		}
		clientEntity.setName(clientVO.getName());
		clientEntity.setAge(clientVO.getAge());
		if(clientVO.getWeather() != null) {
			clientEntity.setWeather(buildWeatherEntityByVo(clientVO.getWeather()));
		}
		
		return clientEntity;
	}
	
	private WeatherEntity buildWeatherEntityByVo(WeatherVO weather) {
		WeatherEntity weatherEntity = new WeatherEntity();
		weatherEntity.setMaxTemp(weather.getMaxTemp());
		weatherEntity.setMinTemp(weather.getMinTemp());
		weatherEntity.setInsertDate(new Date());

		return weatherEntity;
	}

	public ClientVO mapperClientEntityToClientVo(ClientEntity clientEntity) {
		ClientVO clientVO = new ClientVO();
		
		clientVO.setClientId(clientEntity.getClientId());
		clientVO.setName(clientEntity.getName());
		clientVO.setAge(clientEntity.getAge());
		clientVO.setWeather(buildWeatherVoByEntity(clientEntity.getWeather()));
		
		return clientVO;
	}
	
	private WeatherVO buildWeatherVoByEntity(WeatherEntity weatherEntity) {
		WeatherVO weatherVO = new WeatherVO();
		weatherVO.setMaxTemp(weatherEntity.getMaxTemp());
		weatherVO.setMinTemp(weatherEntity.getMinTemp());
		
		return weatherVO;
	}

	public List<ClientVO> mapperClientEntityListToClientVoList(List<ClientEntity> clientEntityList){
		List<ClientVO> clientVoList = new ArrayList<ClientVO>();
		
		for(ClientEntity clientEntity : clientEntityList) {
			clientVoList.add(mapperClientEntityToClientVo(clientEntity));
		}
		
		return clientVoList;
	}
}

package br.com.api.clientRegister.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.clientRegister.entity.ClientEntity;
import br.com.api.clientRegister.exception.DataNotFoundException;
import br.com.api.clientRegister.integration.ipVigilant.IpVigilanteIntegration;
import br.com.api.clientRegister.integration.metaWeather.MetaWeatherIntegration;
import br.com.api.clientRegister.mapper.ClientMapper;
import br.com.api.clientRegister.repository.ClientRepository;
import br.com.api.clientRegister.vo.ClientVO;
import br.com.api.clientRegister.vo.GeoLocationVO;
import br.com.api.clientRegister.vo.WeatherVO;

/**
 * 
 * @author brunosouza
 *
**/

@Service
public class ClientServicesImpl implements ClientServices {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientMapper clientMapper;
	
	@Autowired
	private IpVigilanteIntegration ipVigilanteIntegration;
	
	@Autowired
	private MetaWeatherIntegration metaWeatherIntegration;
	
	@Override
	public ClientVO newClient(ClientVO clientVO, HttpServletRequest request) {
		WeatherVO weatherVO = null;
		try {
			GeoLocationVO geoLocationVO = getGeoLocation(request);
			
			if(geoLocationVO != null) {
				weatherVO = getWeatherByGeoLoaction(geoLocationVO);
			}
			
			if(weatherVO != null) {
				clientVO.setWeather(weatherVO);
			}
		}catch(Exception e) {
			StringBuilder error = new StringBuilder(e.getMessage())
									  .append("O cliente sera salvo, mas sem as informacoes de clima.");
			System.out.println(error);
		}
		
		return clientMapper.mapperClientEntityToClientVo(
				clientRepository.save(clientMapper.mapperClientVoToClienteEntity(clientVO)));
	}

	private WeatherVO getWeatherByGeoLoaction(GeoLocationVO geoLocationVO) {
		WeatherVO weatherVO = metaWeatherIntegration.callMetaWeather(geoLocationVO.getLatitude(), geoLocationVO.getLongitude());
		
		return weatherVO;
	}

	private GeoLocationVO getGeoLocation(HttpServletRequest request) throws Exception {
		GeoLocationVO geoLocationVO = null;
		String clientIp = getIpByRequest(request);
		
		if(clientIp != null) {
			geoLocationVO = ipVigilanteIntegration.callServiceToGetLocationByIp(clientIp);

		}
		
		return geoLocationVO;
	}

	private String getIpByRequest(HttpServletRequest request) throws Exception {
		String ip = null;
		
		if(request != null) {
			ip = request.getHeader("X-FORWARDED-FOR");
			
			if(ip == null || ip.equals("")) {
				ip = request.getRemoteAddr();
			}
		}
		
		if(ip == null) {
			throw new Exception("Nao foi possivel recuperar o IP da requisicao do cliente.");
		}
		
		return ip;
	}

	@Override
	public ClientVO getClientById(int clientId) throws DataNotFoundException{
		try {
			return (ClientVO) clientMapper.mapperClientEntityToClientVo(clientRepository.findById(clientId).get());
		}catch(Exception e) {
			throw new DataNotFoundException("Client", "id", clientId);
		}

	}

	@Override
	public List<ClientVO> getAllClients() {
		return clientMapper.mapperClientEntityListToClientVoList((List<ClientEntity>)clientRepository.findAll());
	}

	@Override
	public ClientVO updateClient(ClientVO clientVO) {
		return clientMapper.mapperClientEntityToClientVo(
				clientRepository.save(clientMapper.mapperClientVoToClienteEntity(clientVO)));

	}

	@Override
	public void removeClient(int clientId) throws DataNotFoundException {
		try {
			clientRepository.deleteById(clientId);
		}catch(Exception e) {
			throw new DataNotFoundException("Client", "id", clientId);
		}
	}

}

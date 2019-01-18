package br.com.api.clientRegister.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.api.clientRegister.exception.DataNotFoundException;
import br.com.api.clientRegister.vo.ClientVO;

/**
 * 
 * @author brunosouza
 *
**/

public interface ClientServices {

	ClientVO newClient(ClientVO clientVO, HttpServletRequest request);
	
	ClientVO getClientById(int clientId) throws DataNotFoundException;
	
	List<ClientVO> getAllClients();
	
	ClientVO updateClient(ClientVO clientVO);
	
	void removeClient(int clientId) throws DataNotFoundException;
}

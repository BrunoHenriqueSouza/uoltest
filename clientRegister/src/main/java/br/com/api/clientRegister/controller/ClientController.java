package br.com.api.clientRegister.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.clientRegister.exception.DataNotFoundException;
import br.com.api.clientRegister.service.ClientServices;
import br.com.api.clientRegister.vo.RequestClientResourceVO;
import br.com.api.clientRegister.vo.ResponseClientListResourceVO;
import br.com.api.clientRegister.vo.ResponseClientResourceVO;

/**
 * 
 * @author brunosouza
 *
**/

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientServices clientServices;
	
	@PostMapping("/new")
	public ResponseEntity<ResponseClientResourceVO> newClient(@Valid @RequestBody RequestClientResourceVO requestVO, 
			HttpServletRequest request) {
		HttpStatus status;
		ResponseClientResourceVO responseClient = new ResponseClientResourceVO();
		
		try {
			status = HttpStatus.CREATED;
			responseClient.setClient(clientServices.newClient(requestVO.getClient(), request));
		}catch(Exception e) {
			status =  HttpStatus.UNPROCESSABLE_ENTITY;
			responseClient.setErrorCode(status);
			responseClient.setMessage("Nao foi possivel criar o cliente.");
		}
		
		return ResponseEntity.status(status)
				 .contentType(MediaType.APPLICATION_JSON)
				 .body(responseClient);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseClientResourceVO> getClientById(@PathVariable(value="id") Integer clientId) 
			throws DataNotFoundException {
		HttpStatus status;
		ResponseClientResourceVO responseClient = new ResponseClientResourceVO();
		
		try {
			status = HttpStatus.OK;
			responseClient.setClient(clientServices.getClientById(clientId));
		}catch(DataNotFoundException e) {
			status =  HttpStatus.UNPROCESSABLE_ENTITY;
			responseClient.setErrorCode(status);
			responseClient.setMessage("Nao foi possivel recuperar o cliente informado.");
		}

		return ResponseEntity.status(status)
				 .contentType(MediaType.APPLICATION_JSON)
				 .body(responseClient);
	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseClientListResourceVO> getAllClients(){
		HttpStatus status;
		ResponseClientListResourceVO responseClientList = new ResponseClientListResourceVO();
		
		try {
			status = HttpStatus.OK;
			responseClientList.setClients(clientServices.getAllClients());
		}catch(DataNotFoundException e) {
			status =  HttpStatus.UNPROCESSABLE_ENTITY;
			responseClientList.setErrorCode(status);
			responseClientList.setMessage("Nao foi possivel recuperar a lista de clientes.");
		}

		return ResponseEntity.status(status)
				 .contentType(MediaType.APPLICATION_JSON)
				 .body(responseClientList);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseClientResourceVO> updateClient(@Valid @RequestBody RequestClientResourceVO requestVO) {
		HttpStatus status;
		ResponseClientResourceVO responseClient = new ResponseClientResourceVO();
		
		try {
			status = HttpStatus.CREATED;
			responseClient.setClient(clientServices.updateClient(requestVO.getClient()));
		}catch(Exception e) {
			status =  HttpStatus.UNPROCESSABLE_ENTITY;
			responseClient.setErrorCode(status);
			responseClient.setMessage("Nao foi possivel atualizar o cliente.");
		}
		
		return ResponseEntity.status(status)
				 .contentType(MediaType.APPLICATION_JSON)
				 .body(responseClient);
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<ResponseClientResourceVO> removeClient(@PathVariable(value="id") Integer clientId) {
		HttpStatus status;
		ResponseClientResourceVO responseClient = new ResponseClientResourceVO();
		
		try {
			clientServices.removeClient(clientId);
			status = HttpStatus.OK;
		}catch(Exception e) {
			status =  HttpStatus.UNPROCESSABLE_ENTITY;
			responseClient.setErrorCode(status);
			responseClient.setMessage("Nao foi possivel deletar o cliente.");
		}
		
		return ResponseEntity.status(status)
				 .contentType(MediaType.APPLICATION_JSON)
				 .body(responseClient);
	}
	
}

package br.com.api.clientRegister.clientTest;

import org.mockito.MockitoAnnotations;

//@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

	//@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	/*
	 * Blocos de testes para chamadas que criarao um cliente 
	 */

	public void callClientResourceToCreateANewClientWithValidIpAndValidateReturnWithWeather() {

	}
	

	public void callClientResourceToCreateANewClientWithInvalidIpAndValidateReturnWithoutWeather() {
		
	}
	
	/*
	 * Blocos de testes para chamadas que atualizarao um cliente 
	 */

	public void callClientResourceToUpdateAClientWithWeatherAndValidateReturnWithUpdate() {
		
	}
	

	public void callClientResourceToUpdateAClientWithoutWeatherAndValidateReturnWithUpdate() {
		
	}
	
}

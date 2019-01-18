package br.com.api.clientRegister.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.api.clientRegister.entity.ClientEntity;

/**
 * 
 * @author brunosouza
 *
**/

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Integer>{

}

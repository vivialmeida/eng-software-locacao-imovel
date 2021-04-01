package edu.ifma.locacaodeimoveis.repository;

import edu.ifma.locacaodeimoveis.model.Cliente;
import javax.persistence.EntityManager;



public class ClienteRepository extends GenericRepository<Cliente> {
	
	public ClienteRepository(EntityManager manager) {
		super(manager, Cliente.class);
	}

}

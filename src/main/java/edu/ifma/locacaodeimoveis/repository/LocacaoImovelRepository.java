package edu.ifma.locacaodeimoveis.repository;

import edu.ifma.locacaodeimoveis.model.Cliente;
import edu.ifma.locacaodeimoveis.model.LocacaoImovel;


import java.util.List;


import javax.persistence.EntityManager;


public class LocacaoImovelRepository extends GenericRepository<LocacaoImovel> {
	
	private EntityManager manager;
	
	public LocacaoImovelRepository(EntityManager manager) {
		super(manager, LocacaoImovel.class);
		this.manager = manager;
	}
	
	public List<LocacaoImovel> listaTodasLocacoesDoCliente(Cliente cliente) throws Exception {

		return manager.createQuery("from LocacaoImovel li where li.cliente = :cliente", LocacaoImovel.class)
					  .setParameter("cliente", cliente)
					  .getResultList();	
	
	}
	
	public List<LocacaoImovel> listaTodasLocacoes() throws Exception {
		
		return manager.createQuery("from LocacaoImovel", LocacaoImovel.class)
					  .getResultList();
		
	}

}

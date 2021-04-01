package edu.ifma.locacaodeimoveis.repository;

import edu.ifma.locacaodeimoveis.model.Imovel;
import edu.ifma.locacaodeimoveis.model.TipoImovel;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

public class ImovelRepository extends GenericRepository<Imovel> {

	private final EntityManager manager;
	
	public ImovelRepository(EntityManager manager) {
		super(manager, Imovel.class);
		this.manager = manager;
	}
	
	public List<Imovel> listaDeImoveisAbaixoDoValor(BigDecimal valor) throws Exception {
		return manager.createQuery("from Imovel where valorAluguelSugerido <= :valor", Imovel.class)
					  .setParameter("valor", valor)
					  .getResultList();
	}

	public List<Imovel> listaDeImoveisPorTipo(TipoImovel tipo) throws Exception {
		return manager.createQuery("from Imovel where tipo_imovel = :tipo", Imovel.class)
				.setParameter("tipo", tipo)
				.getResultList();
	}


	
}

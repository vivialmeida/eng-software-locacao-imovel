package edu.ifma.locacaodeimoveis.repository;

import edu.ifma.locacaodeimoveis.model.Aluguel;
import edu.ifma.locacaodeimoveis.model.Cliente;
import java.util.List;

import javax.persistence.EntityManager;

public class AluguelRepository extends GenericRepository<Aluguel> {
	
	private final EntityManager manager;
	
	public AluguelRepository(EntityManager manager) {
		super(manager, Aluguel.class);
		this.manager = manager;
	}
	
	public List<Aluguel> listaTodosAlugueisDoInquilino(Cliente cliente) throws Exception {
		
		return manager.createQuery("from Aluguel a where a.locacao.cliente = :cliente", Aluguel.class)
					  .setParameter("cliente", cliente)
					  .getResultList();
		
	}
	
	public List<Aluguel> listaTodosAlugueisPagosDoInquilino(Cliente cliente) throws Exception {
		
		return manager.createQuery("from Aluguel a where a.locacao.cliente = :cliente and a.dataPagamento is not null", Aluguel.class)
					  .setParameter("cliente", cliente)
					  .getResultList();
	}
	
	public List<Aluguel> listaDeTodosAlugueisPagosEmAtrasoNaDataDeVencimento() throws Exception {
		
		return manager.createQuery("from Aluguel a where dataPagamento > dataVencimento", Aluguel.class)
					  .getResultList();
	}
}

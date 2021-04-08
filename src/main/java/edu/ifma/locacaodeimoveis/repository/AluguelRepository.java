package edu.ifma.locacaodeimoveis.repository;

import edu.ifma.locacaodeimoveis.model.Aluguel;
import edu.ifma.locacaodeimoveis.model.Cliente;

import java.time.LocalDate;
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
	

	public List<Aluguel> listaDeTodosAlugueisPagosEmAtrasoNaDataDeVencimento() throws Exception {
		
		return manager.createQuery("from Aluguel a where dataPagamento > dataVencimento", Aluguel.class)
					  .getResultList();
	}

	public List<Aluguel> emAtraso() {
		return manager
			.createQuery( "from Aluguel  a from  where a.dataPagamento is null and a.dataVencimento < :hoje", Aluguel.class)
			.setParameter("hoje", LocalDate.now() )
			.getResultList();
	}
}

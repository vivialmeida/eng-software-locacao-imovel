package edu.ifma.locacaodeimoveis.service;

import edu.ifma.locacaodeimoveis.model.Aluguel;
import edu.ifma.locacaodeimoveis.model.Cliente;
import edu.ifma.locacaodeimoveis.model.LocacaoImovel;
import edu.ifma.locacaodeimoveis.repository.AluguelRepository;
import edu.ifma.locacaodeimoveis.util.JpaUtil;
import java.util.List;

import javax.persistence.EntityManager;

public class AluguelService{

	
	private  EntityManager MANAGER = JpaUtil.getEntityManager();
	private AluguelRepository repositorio = new AluguelRepository(MANAGER);
	private EmailService emailService = new EmailService();

	private LocacaoImovelService locacaoImovelService = new LocacaoImovelService();

	
	public void adicionaOuAtualizaAluguel(Aluguel aluguel) throws NegocioException {
		
		try {
			if (aluguel.getId() == null) {
				validaValorAluguel(aluguel);
				repositorio.salva(aluguel);
			} else {
				repositorio.atualiza(aluguel);
			}

		} catch (Exception e) {
			throw new NegocioException("Aluguel não pode ser salvo/atualizado no banco de dados" + e);
		}
		
	}

	private void validaValorAluguel(Aluguel aluguel) throws NegocioException {
		LocacaoImovel locacaoImovel =  locacaoImovelService.buscaPorId(aluguel.getLocacao().getId());
		 if(aluguel.getValorPago().compareTo(locacaoImovel.getValorAluguel()) <0){
		 	throw new NegocioException("Valor do aluguel é inferior ao da locacao ");
		 }



	}

	public void exluiAluguel(Integer id) throws NegocioException {
			
			try {

				repositorio.exclui(id);
				
			} catch (Exception e) {
				
				throw new NegocioException("Aluguel não pode ser exluido no banco de dados" + e);

			}
	}
	
	
	public List<Aluguel> listaTodosOsAlugueis() throws NegocioException {
		
		try {

			return repositorio.lista();
			
		} catch (Exception e) {
			
			throw new NegocioException("Nenhum Aluguel cadastrado no banco de dados " + e);
			
		}
	}
	
	public Aluguel buscaPorId(Integer id) throws NegocioException {
		
		try {
			
			return repositorio.buscaPorId(id);

		} catch(Exception e) {
			
			throw new NegocioException("Não existe Aluguel com esse id no banco de dados " + e);
		
		}
	}
	
	public List<Aluguel> listaTodosAlugueisDoInquilino(Cliente cliente) throws NegocioException {
		
		try {
			
			return repositorio.listaTodosAlugueisDoInquilino(cliente);
			
		} catch (Exception e) {
			
			throw new NegocioException("Não é possível listar Alugueis deste Cliente " + e);
			
		}
		
	}
	

	public List<Aluguel> listaDeTodosAlugueisPagosEmAtrasoNaDataDeVencimento() throws NegocioException {
		
		try {
			
			return repositorio.listaDeTodosAlugueisPagosEmAtrasoNaDataDeVencimento();
			
		} catch (Exception e) {
			
			throw new NegocioException("Não é possível listar Alugueis que foram pagos em atraso da dataVencimento " + e);
		}
	}

	public void notificaUsuariosEmAtraso() {

		List<Aluguel> alugueisAtrasados =  repositorio.emAtraso();

		alugueisAtrasados.forEach(aluguel ->
			emailService.enviarEmailaluguel(aluguel.getLocacao().getCliente()
			));
	}

	public void closeRecursos() {
		MANAGER.close();
		JpaUtil.close();
	}

	public void  setFields(EntityManager entityManager, AluguelRepository repositorio){
		this.MANAGER = entityManager;
		this.repositorio = repositorio;



	}
	
	
}

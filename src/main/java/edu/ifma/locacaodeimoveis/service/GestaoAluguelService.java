package edu.ifma.locacaodeimoveis.service;

import edu.ifma.locacaodeimoveis.model.Aluguel;
import edu.ifma.locacaodeimoveis.model.Cliente;
import edu.ifma.locacaodeimoveis.model.LocacaoImovel;
import edu.ifma.locacaodeimoveis.repository.AluguelRepository;
import edu.ifma.locacaodeimoveis.util.JpaUtil;

import java.util.List;

import javax.persistence.EntityManager;

public class GestaoAluguelService extends GenericService<Aluguel> {
	
	private static final EntityManager MANAGER = JpaUtil.getEntityManager();
	private static final AluguelRepository repositorio = new AluguelRepository(MANAGER);
	private  EmailService emailService;

	public GestaoAluguelService() {
		super(MANAGER, repositorio);
	}
	
	
	public void adicionaOuAtualizaAluguel(Aluguel aluguel) throws NegocioException {
		
		try {
			if (aluguel.getId() == null) {
				super.salvaObjeto(aluguel);
			} else {
				super.atualizaObjeto(aluguel);
			}

		} catch (Exception e) {
			throw new NegocioException("Aluguel não pode ser salvo/atualizado no banco de dados" + e);
		}
		
	}
	
	public void exluiAluguel(Integer id) throws NegocioException {
			
			try {
				
				super.exluiObjeto(id);
				
			} catch (Exception e) {
				
				throw new NegocioException("Aluguel não pode ser exluido no banco de dados" + e);

			}
	}
	
	
	public List<Aluguel> listaTodosOsAlugueis() throws NegocioException {
		
		try {

			return super.listaObjetos();
			
		} catch (Exception e) {
			
			throw new NegocioException("Nenhum Aluguel cadastrado no banco de dados " + e);
			
		}
	}
	
	public Aluguel buscaPorId(Integer id) throws NegocioException {
		
		try {
			
			return super.buscaPorId(id);

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
	
	public List<Aluguel> listaTodosAlugueisPagosDoInquilino(Cliente cliente) throws NegocioException {
		
		try {
			
			return repositorio.listaTodosAlugueisPagosDoInquilino(cliente);
			
		} catch (Exception e) {
			
			throw new NegocioException("Não é possível listar Alugueis pagos deste Cliente " + e);
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
			emailService.notifica(aluguel.getLocacao().getCliente()
			));
	}
	
	public void closeRecursos() {
		MANAGER.close();
		JpaUtil.close();
	}

	
	
}

package edu.ifma.locacaodeimoveis.service;

import java.util.List;

import javax.persistence.EntityManager;
import edu.ifma.locacaodeimoveis.model.Cliente;
import edu.ifma.locacaodeimoveis.repository.ClienteRepository;
import edu.ifma.locacaodeimoveis.util.JpaUtil;

public class ClienteService {

	private EntityManager MANAGER = JpaUtil.getEntityManager();
	private ClienteRepository repositorio = new ClienteRepository(MANAGER);

	
	public void adicionaOuAtualizaCliente(Cliente cliente) throws NegocioException {
		
		try {
			
			if (cliente.getId() == null) {
				repositorio.salva(cliente);
			} else {
				repositorio.atualiza(cliente);
			}

		} catch (Exception e) {
			
			throw new NegocioException("Cliente não pode ser salvo/atualizado no banco de dados" + e);
			
		}
		
	}
	
	public void exluiCliente(Integer id) throws NegocioException {
		
		try {
			
			repositorio.exclui(id);
			
		} catch (Exception e) {
			
			throw new NegocioException("Cliete não pode ser exluido no banco de dados" + e);
			
		}
	}
	
	public List<Cliente> listaTodosOsClientes() throws NegocioException {

		try {
			
			return repositorio.lista();
			
		} catch (Exception e) {
			
			throw new NegocioException("Nenhum profissional cadastrado no banco de dados " + e);
			
		}
		
	}
	
	
	public Cliente buscaPorId(Integer id) throws NegocioException {
		
		try {
			
			return repositorio.buscaPorId(id);
			
		} catch (Exception e) {
			
			throw new NegocioException("Não existe Cliente com esse id no banco de dados " + e);
		}
		
	}
	
	public void closeRecursos() {
		MANAGER.close();
		JpaUtil.close();
	}

	public void serFields(EntityManager entityManager, ClienteRepository repositorio){
		this.MANAGER = entityManager;
		this.repositorio =repositorio;

	}
	
	
}

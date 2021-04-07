package edu.ifma.locacaodeimoveis.service;

import java.util.List;

import javax.persistence.EntityManager;
import edu.ifma.locacaodeimoveis.model.Cliente;
import edu.ifma.locacaodeimoveis.repository.ClienteRepository;
import edu.ifma.locacaodeimoveis.util.JpaUtil;

public class ClienteService extends GenericService<Cliente> {

	private static final EntityManager MANAGER = JpaUtil.getEntityManager();
	private static final ClienteRepository repositorio = new ClienteRepository(MANAGER);
	
	
	public ClienteService() {
		super(MANAGER, repositorio);
	}
	
	
	public void adicionaOuAtualizaCliente(Cliente cliente) throws NegocioException {
		
		try {
			
			if (cliente.getId() == null) {
				super.salvaObjeto(cliente);
			} else {
				super.atualizaObjeto(cliente);
			}

		} catch (Exception e) {
			
			throw new NegocioException("Cliente não pode ser salvo/atualizado no banco de dados" + e);
			
		}
		
	}
	
	public void exluiCliente(Integer id) throws NegocioException {
		
		try {
			
			super.exluiObjeto(id);
			
		} catch (Exception e) {
			
			throw new NegocioException("Cliete não pode ser exluido no banco de dados" + e);
			
		}
	}
	
	public List<Cliente> listaTodosOsClientes() throws NegocioException {

		try {
			
			return super.listaObjetos();
			
		} catch (Exception e) {
			
			throw new NegocioException("Nenhum profissional cadastrado no banco de dados " + e);
			
		}
		
	}
	
	
	public Cliente buscaPorId(Integer id) throws NegocioException {
		
		try {
			
			return super.buscaPorId(id);
			
		} catch (Exception e) {
			
			throw new NegocioException("Não existe Cliente com esse id no banco de dados " + e);
		}
		
	}
	
	public void closeRecursos() {
		MANAGER.close();
		JpaUtil.close();
	}
	
	
}

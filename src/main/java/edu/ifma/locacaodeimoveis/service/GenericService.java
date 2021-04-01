package edu.ifma.locacaodeimoveis.service;

import java.util.List;
import javax.persistence.EntityManager;
import edu.ifma.locacaodeimoveis.repository.GenericRepository;


abstract class GenericService<T> {
	
	
	private final EntityManager MANAGER;
	private final GenericRepository<T> repositorio;
	
	
	GenericService(EntityManager manager, GenericRepository<T> repositorio) {
		this.MANAGER = manager;
		this.repositorio = repositorio;
	}
	
	void salvaObjeto(T t) throws Exception {
		
		try {
			
			MANAGER.getTransaction().begin();
			repositorio.salva(t);
			MANAGER.getTransaction().commit();
		
		} catch (Exception e) {
			
			MANAGER.getTransaction().rollback();
			throw new Exception(e);
			
		}
		
	}

	void atualizaObjeto(T t) throws Exception {
		
		try {
			
			MANAGER.getTransaction().begin();
			repositorio.atualiza(t);
			MANAGER.getTransaction().commit();
		
		} catch (Exception e) {
			
			MANAGER.getTransaction().rollback();
			throw new Exception(e);
			
		}
		
	}
	
	void exluiObjeto(Object id) throws Exception {
		
		try {
			
			MANAGER.getTransaction().begin();
			repositorio.exclui(id);
			MANAGER.getTransaction().commit();
			
		} catch (Exception e) {
			
			MANAGER.getTransaction().rollback();
			throw new Exception(e);
		
		}
		
	}
	
	
	List<T> listaObjetos() throws Exception {
		
		try {
			
			return repositorio.lista();
			
		} catch (Exception e) {
			
			throw new Exception(e);
			
		}
	}
	
	T buscaPorId(Integer id) throws Exception {
		
		try {
			
			return repositorio.buscaPorId(id);
			
		} catch(Exception e) {
			
			throw new Exception(e);
		
		}
	}
	
	
	
	
}

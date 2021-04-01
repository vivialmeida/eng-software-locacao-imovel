package edu.ifma.locacaodeimoveis.repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import java.util.List;

public abstract class GenericRepository<T> {
	
	private EntityManager manager;
	private Class<T> clazz;
	
	public GenericRepository(EntityManager manager, Class<T> clazz) {
		this.manager = manager;
		this.clazz = clazz;
	}
	
	public void salva(T t) throws Exception {

		this.manager.persist(t);
	}
	
	public void atualiza(T t) throws Exception {
		
		this.manager.merge(t);
			
	}
	
	public void exclui(Object id) throws Exception {
		
		T t = buscaPorId(id);
		this.manager.remove(t);
		this.manager.flush();
			
	}
	
	public List<T> lista() throws Exception {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(clazz);
		query.from(clazz);
		return manager.createQuery(query).getResultList();
		
	}
	
	public T buscaPorId(Object id) throws Exception {
		
		return this.manager.find(clazz, id);
	}
	

}

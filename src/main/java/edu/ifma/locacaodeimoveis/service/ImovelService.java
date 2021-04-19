package edu.ifma.locacaodeimoveis.service;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import edu.ifma.locacaodeimoveis.model.Imovel;
import edu.ifma.locacaodeimoveis.model.TipoImovel;
import edu.ifma.locacaodeimoveis.repository.ImovelRepository;
import edu.ifma.locacaodeimoveis.util.JpaUtil;

public class ImovelService{
	
	private EntityManager MANAGER = JpaUtil.getEntityManager();
	private  ImovelRepository repositorio = new ImovelRepository(MANAGER);

	
	public void adicionaOuAtualizaImovel(Imovel imovel) throws NegocioException {
		
		try {

			if (imovel.getId() == null) {
				repositorio.salva(imovel);
			} else {
				repositorio.atualiza(imovel);
			}
			
	
		} catch (Exception e) {
			
			throw new NegocioException("Imovel não pode ser salvo/atualizado no banco de dados" + e);
		}
		
	}
	
	
	public void excluiImovel(Integer id) throws NegocioException {
		
		try {
			
			repositorio.exclui(id);
			
		} catch (Exception e) {
			
			throw new NegocioException("Imovel não pode ser exluido no banco de dados" + e);
			
		}
	}
	
	
	public List<Imovel> listaTodosOsImoveis() throws NegocioException {
		
		try {
			
			return repositorio.lista();
			
		} catch (Exception e) {
			
			throw new NegocioException("Não existe imoveis no banco de dados" + e);
			
		}
		
	}
	
	public Imovel buscaPorId(Integer id) throws NegocioException {
		
		try {
			
			return repositorio.buscaPorId(id);
			
		} catch(Exception e) {
			
			throw new NegocioException("Não existe Imovel com esse id no banco de dados  " + e);
			
		}
		
	}
	
	public List<Imovel> listaDeImoveisAbaixoDoValor(BigDecimal valor) throws NegocioException {
		
		try {
			
			return repositorio.listaDeImoveisAbaixoDoValor(valor);
			
		} catch (Exception e) {
			
			throw new NegocioException("Não existe Imoveis com valor abaixo do informado no banco de dados  " + e);
		}
	}public List<Imovel> listaDeImoveisPorTipo(TipoImovel tipo) throws NegocioException {

		try {

			return repositorio.listaDeImoveisPorTipo(tipo);

		} catch (Exception e) {

			throw new NegocioException("Não existe Imoveis com valor abaixo do informado no banco de dados  " + e);
		}
	}


	
	public void closeRecursos() {
		MANAGER.close();
		JpaUtil.close();
	}


	public  void setManager(EntityManager entityManager){
		MANAGER = entityManager;
	}

	public  void setRepositorio(ImovelRepository repositorio){
		this.repositorio = repositorio;
	}
}

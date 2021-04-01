package edu.ifma.locacaodeimoveis.service;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import edu.ifma.locacaodeimoveis.model.Imovel;
import edu.ifma.locacaodeimoveis.repository.ImovelRepository;
import edu.ifma.locacaodeimoveis.util.JpaUtil;

public class GestaoImovelService extends GenericService<Imovel> {
	
	private static final EntityManager MANAGER = JpaUtil.getEntityManager();
	private static final ImovelRepository repositorio = new ImovelRepository(MANAGER);


	public GestaoImovelService() {
		super(MANAGER, repositorio);
	}
	
	public void adicionaOuAtualizaImovel(Imovel imovel) throws NegocioException {
		
		try {

			if (imovel.getId() == null) {
				super.salvaObjeto(imovel);
			} else {
				super.atualizaObjeto(imovel);
			}
			
	
		} catch (Exception e) {
			
			throw new NegocioException("Imovel não pode ser salvo/atualizado no banco de dados" + e);
		}
		
	}
	
	
	public void excluiImovel(Integer id) throws NegocioException {
		
		try {
			
			super.exluiObjeto(id);
			
		} catch (Exception e) {
			
			throw new NegocioException("Imovel não pode ser exluido no banco de dados" + e);
			
		}
	}
	
	
	public List<Imovel> listaTodosOsImoveis() throws NegocioException {
		
		try {
			
			return super.listaObjetos();
			
		} catch (Exception e) {
			
			throw new NegocioException("Não existe imoveis no banco de dados" + e);
			
		}
		
	}
	
	public Imovel buscaPorId(Integer id) throws NegocioException {
		
		try {
			
			return super.buscaPorId(id);
			
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
	}
	
	public void closeRecursos() {
		MANAGER.close();
		JpaUtil.close();
	}

}

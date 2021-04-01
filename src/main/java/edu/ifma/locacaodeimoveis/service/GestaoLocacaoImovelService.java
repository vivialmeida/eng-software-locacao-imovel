package edu.ifma.locacaodeimoveis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import edu.ifma.locacaodeimoveis.model.Cliente;
import edu.ifma.locacaodeimoveis.model.LocacaoImovel;
import edu.ifma.locacaodeimoveis.repository.LocacaoImovelRepository;
import edu.ifma.locacaodeimoveis.util.JpaUtil;

public class GestaoLocacaoImovelService extends GenericService<LocacaoImovel> {

	private static final EntityManager MANAGER = JpaUtil.getEntityManager();
	private static final LocacaoImovelRepository repositorio = new LocacaoImovelRepository(MANAGER);
	
	public GestaoLocacaoImovelService() {
		super(MANAGER, repositorio);
	}
	
	public void adicionaOuAtualizaLocaoImovel(LocacaoImovel locacaoImovel) throws NegocioException {
		
		try {
			
			if (locacaoImovel.getId() == null) {
				super.salvaObjeto(locacaoImovel);
			} else {
				super.atualizaObjeto(locacaoImovel);
			}
			
		} catch (Exception e) {
			
			throw new NegocioException("LocacaoImovel não pode ser salvo/atualizado no banco de dados" + e);
		
		}
		
	}
	
	
	public void excluiLocacaoImovel(Integer id) throws NegocioException {
		
		try {
			
			super.exluiObjeto(id);
			
		} catch (Exception e) {
			
			throw new NegocioException("LocacaoImovel não pode ser exluido no banco de dados" + e);
			
		}
		
	}
	
	public List<LocacaoImovel> listaTodasAsLocacoesImoveis() throws NegocioException {
		
		try {
			
			return super.listaObjetos();
			
		} catch (Exception e) {
			
			throw new NegocioException("Nenhuma LocacaoImovel cadastrado no banco de dados " + e);
			
		}
		
	}
	
	public LocacaoImovel buscaPorId(Integer id) throws NegocioException {
		
		try {
			
			return super.buscaPorId(id);
			
		} catch (Exception e) {
			
			throw new NegocioException("Não existe LocacoImovel com esse id no banco de dados " + e);
			
		}
		
	}
	
	
	public List<LocacaoImovel> listaTodasLocacoesDoCliente(Cliente cliente) throws NegocioException {
		
		try {
			
			return repositorio.listaTodasLocacoesDoCliente(cliente);
			
		} catch (Exception e) {
			
			throw new NegocioException("Não existe LocacaoImovel deste cliente no banco de dados " + e);
		}
		
	}
	
	public Map<Cliente, List<LocacaoImovel>> listaTodasLocacoesPorCliente() throws NegocioException {
		
		try {
			
			Map<Cliente, List<LocacaoImovel>> mapaDeLocacoes = new HashMap<Cliente, List<LocacaoImovel>>();
			List<LocacaoImovel> locacoes = repositorio.listaTodasLocacoes();
		
			
			for (LocacaoImovel locacaoImovel : locacoes) {
				mapaDeLocacoes.put(locacaoImovel.getCliente(), new ArrayList<LocacaoImovel>());
			}
			
			for (LocacaoImovel locacaoImovel : locacoes) {
				mapaDeLocacoes.get(locacaoImovel.getCliente()).add(locacaoImovel);
			}
			
			return mapaDeLocacoes;
			
			
		} catch (Exception e) {
			
			throw new NegocioException("Não é possivel  agrupar LocacaoImovel por Cliente no banco de dados " + e);
		}
	}
	
	public void closeRecursos() {
		MANAGER.close();
		JpaUtil.close();
	}
	
	
	
}

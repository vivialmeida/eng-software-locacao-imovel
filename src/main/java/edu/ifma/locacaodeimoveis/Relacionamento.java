package edu.ifma.locacaodeimoveis;




import edu.ifma.locacaodeimoveis.model.Cliente;
import edu.ifma.locacaodeimoveis.repository.ClienteRepository;
import edu.ifma.locacaodeimoveis.util.JpaUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;




public class Relacionamento {
	
	public static void main(String[] args) throws Exception {
		
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		

		Cliente cliente = new Cliente();
		cliente.setNome("Carlos Augusto");
		cliente.setCpf("756812241-50");
		cliente.setRg("123048979634-24");
		cliente.setCep("65135-000");
		cliente.setEndereco("Rua 25, numero 76, Maiobs");
		cliente.setTelefone1("323896456");
		cliente.setEmail("carlosAugs@gmail.com");
		
		

		
		ClienteRepository cr = new ClienteRepository(manager);
		System.out.println(cr.buscaPorId(1));

		


		
		
//		Cliente cliente = (new ClienteRepository(manager)).buscaPorId(1); 			//Trazer para o estado mananger
//		TipoImovel tipoImovel = (new TipoImovelRepository(manager)).buscaPorId(1);	//Trazer para o estado manager
//
//
//
//		EnderecoImovel enderecoImovel = new EnderecoImovel();
//		enderecoImovel.setLogradouro("RUA 115");
//		enderecoImovel.setBairro("Maiobao");
//		enderecoImovel.setCep("651350-000");
//		enderecoImovel.setPontoDeReferencia("Proximo ao Campo");
//		enderecoImovel.setZonaCidade(ZonaCidade.LESTE);
//
//		Imovel imovel = new Imovel();
//		imovel.setNomeImovel("AP Maiobão");
//		imovel.setEnderecoImovel(enderecoImovel);
//		imovel.setMetragem(new BigDecimal(55.2));
//		imovel.setQuantidadeDomitorios(2);
//		imovel.setQuantidadeBanheiros(1);
//		imovel.setQuantidadeSuites(1);
//		imovel.setVagasGaragem(1);
//		imovel.setValorAluguelSugerido(new BigDecimal(750.2));
//		imovel.setValorIPTU(new BigDecimal(76.5));
//
//		imovel.setTipoImovel(tipoImovel);
//		imovel.setCliente(cliente);
//
//		GestaoImovelService gis = new GestaoImovelService();
//		gis.adicionaOuAtualizaImovel(imovel);
//
//
//
//		ImovelRepository iR = new ImovelRepository(manager);
//		iR.salva(imovel);
//		System.out.println(iR.buscaPorId(1));
//
//
//
//		Profissional profissional = new Profissional();
//
//		profissional.setNome("Carlos Eduardo");
//		profissional.setProfissao("Advogado");
//		profissional.setTelefone1("32371366");
//		profissional.setValorHora(new BigDecimal(50.25));
//
//		ProfissionalRepository pR = new ProfissionalRepository(manager);
//		pR.salva(profissional);
//
//
//
//		Imovel imovel = (new ImovelRepository(manager)).buscaPorId(1);
//		Profissional profissional = (new ProfissionalRepository(manager)).buscaPorId(1);
//
//		ServicoImovel servicoImovel = new ServicoImovel();
//		servicoImovel.setValorTotal(new BigDecimal(1200));
//		servicoImovel.setImovel(imovel);
//		servicoImovel.setProfissional(profissional);
//
//		ServicoImovelRepository sIR = new ServicoImovelRepository(manager);
//		sIR.salva(servicoImovel);
//
//
//
//
//		Cliente cliente = (new ClienteRepository(manager)).buscaPorId(1);
//		Imovel imovel = (new ImovelRepository(manager)).buscaPorId(1);
//		Profissional profissional = (new ProfissionalRepository(manager)).buscaPorId(1);
//
//
//		LocacaoImovel locacaoImovel = new LocacaoImovel();
//		locacaoImovel.setAtivo(true);
//		locacaoImovel.setDataFim( LocalDate.of(2019, 07, 20) );
//		locacaoImovel.setDataInicio(LocalDate.now());
//		locacaoImovel.setDiaVencimento(20);
//		locacaoImovel.setPeriodicidade("A cada 6 meses");
//		locacaoImovel.setResponsavelPagamento("José Alvez");
//		locacaoImovel.setValorAluguel(new BigDecimal(250.65));
//		locacaoImovel.setInquilino(cliente);
//		locacaoImovel.setImovel(imovel);
//		locacaoImovel.setProfissional(profissional);
//
//		LocacaoImovelRepository lIR = new LocacaoImovelRepository(manager);
//		lIR.salva(locacaoImovel);
//
//
//
//
//		LocacaoImovel locacaoImovel = (new LocacaoImovelRepository(manager)).buscaPorId(1);
//
//		Aluguel aluguel = new Aluguel();
//		aluguel.setDataPagamento(LocalDate.of(2018, 8, 20));
//		aluguel.setDataVencimento(LocalDate.of(2018, 8, 26));
//		aluguel.setValorPago(new BigDecimal(250.65));
//		aluguel.setLocacoes(locacaoImovel);
//
//		AluguelRepository aR = new AluguelRepository(manager);
//		aR.salva(aluguel);
//
//
//
//		Cliente cliente = null;
//		try {
//			cliente = (new ClienteRepository(manager)).buscaPorId(2);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		System.out.println(cliente);
//
//
//		Profissional profissional = new Profissional();
//
//		profissional.setNome("Carlos Perereira");
//		profissional.setProfissao("carpiteiro");
//		profissional.setTelefone1("32371366");
//		profissional.setValorHora(new BigDecimal(65.25));
//
//
//
//		GestaoProfissionalService gps = new GestaoProfissionalService();
//
//		Profissional prof = gps.buscaPorId(1);
//
//		System.out.println(prof);
//
//		gps.adicionaOuAtualizaProfissional(prof);
//
//		GestaoTipoImovelService gtis = new GestaoTipoImovelService();
//		TipoImovel apartamento = gtis.buscaPorId(2);
//
//		GestaoClienteService gcs = new GestaoClienteService();
//		Cliente cliente = gcs.buscaPorId(1);
//
//		EnderecoImovel enderecoImovel = new EnderecoImovel();
//		enderecoImovel.setLogradouro("RUA 115");
//		enderecoImovel.setBairro("Maiobao");
//		enderecoImovel.setCep("651350-000");
//		enderecoImovel.setPontoDeReferencia("Proximo ao Campo");
//		enderecoImovel.setZonaCidade(ZonaCidade.LESTE);
//
//		Imovel imovel = new Imovel();
//		imovel.setNomeImovel("AP Maiobão");
//		imovel.setEnderecoImovel(enderecoImovel);
//		imovel.setMetragem(new BigDecimal(55.2));
//		imovel.setQuantidadeDomitorios(2);
//		imovel.setQuantidadeBanheiros(1);
//		imovel.setQuantidadeSuites(1);
//		imovel.setVagasGaragem(1);
//		imovel.setValorAluguelSugerido(new BigDecimal(750.2));
//		imovel.setValorIPTU(new BigDecimal(76.5));
//
//		imovel.setTipoImovel(apartamento);
//		imovel.setCliente(cliente);
//
//		GestaoImovelService gis = new GestaoImovelService();
//		gis.adicionaOuAtualizaImovel(imovel);
//
//
//		GestaoClienteService gcs = new GestaoClienteService();
//		Cliente cliente = gcs.buscaPorId(1);
//
//		GestaoLocacaoImovelService glis = new GestaoLocacaoImovelService();
//
//		for (LocacaoImovel l : glis.listaTodasLocacoesDoCliente(cliente)) {
//			System.out.println(l);
//		}
//
//		Query query = manager.createQuery("from LocacaoImovel l where l.cliente = :cliente", LocacaoImovel.class);
//		query.setParameter("cliente", manager.find(Cliente.class, 1));
//		List<LocacaoImovel> locacoes = query.getResultList();
//
//		for (LocacaoImovel l : locacoes) {
//			System.out.println(l);
//		}
//
//
//
//
//		/**Cadastrando Locacões**/
//		GestaoClienteService gcs = new GestaoClienteService();
//		Cliente cliente = gcs.buscaPorId(2);
//
//		GestaoProfissionalService gps = new GestaoProfissionalService();
//		Profissional prof = gps.buscaPorId(5);
//
//		GestaoImovelService gis = new GestaoImovelService();
//		Imovel imovel = gis.buscaPorId(4);
//
//		LocacaoImovel locacao = new LocacaoImovel();
//		locacao.setAtivo(true);
//		locacao.setDataInicio(LocalDate.now());
//		locacao.setDataFim(LocalDate.of(2020, 07, 10));
//		locacao.setDiaVencimento(10);
//		locacao.setPeriodicidade("A cada 1 ano");
//		locacao.setResponsavelPagamento("Pestinha");
//		locacao.setValorAluguel(new BigDecimal(323.56));
//		locacao.setInquilino(cliente);
//		locacao.setImovel(imovel);
//		locacao.setProfissional(prof);
//
//		GestaoLocacaoImovelService glis = new GestaoLocacaoImovelService();
//		glis.adicionaOuAtualizaLocaoImovel(locacao);
//
//
//
//		/**Adicionando novas funcionalidades**/ //repositoryImovel
//		Query query = manager.createQuery("from Imovel where valorAluguelSugerido <= :valor", Imovel.class);
//		List<Imovel> imoveis = query.setParameter("valor", new BigDecimal(152)).getResultList();
//
//		for (Imovel imovel : imoveis) {
//			System.out.println(imovel);
//		}
//
//
////		Lista de todos os alugueis pagos por determinado inquilino
//		Query query = manager.createQuery("from Aluguel a where a.locacao.cliente = :cliente and a.dataPagamento is not null", Aluguel.class)
//							 .setParameter("cliente", manager.find(Cliente.class, 1));
//
//		List<Aluguel> alugueis = query.getResultList();
//
//		for (Aluguel aluguel : alugueis) {
//			System.out.println(aluguel);
//		}

		
		
		tx.commit();
		manager.close();
		JpaUtil.close();
		
		
	}
}

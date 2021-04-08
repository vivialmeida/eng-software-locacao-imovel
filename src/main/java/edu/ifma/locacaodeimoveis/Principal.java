package edu.ifma.locacaodeimoveis;

import edu.ifma.locacaodeimoveis.model.*;
import edu.ifma.locacaodeimoveis.service.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

public class Principal {
	
	public static void main(String[] args) throws NegocioException {

		/**Cliente**/
		Cliente cliente = new Cliente();
		cliente.setCep("65133-000");
		cliente.setCpf("193576792-54");
		cliente.setDataNascimento(LocalDate.of(1996, 11, 13));
		cliente.setEmail("carlos_15@gmail.com");
		cliente.setNome("Carlos Eduardo Cunha da Silva");
		cliente.setRg("006028579634-24");
		cliente.setTelefone1("(98)3237-1355");
		cliente.setTelefone2("(98)98703-8178");
		
		ClienteService gcs = new ClienteService();
		gcs.adicionaOuAtualizaCliente(cliente);
		gcs.closeRecursos();
		
		
		/**Imovel**/
		Cliente proprietario = gcs.buscaPorId(1);

		EnderecoImovel endereco = new EnderecoImovel();
		endereco.setBairro("Cidade Operaria");
		endereco.setCep("65138-000");
		endereco.setLogradouro("Rua 50");
		endereco.setPontoDeReferencia("Proxima ao mercadinho da josefina");
		endereco.setZonaCidade(ZonaCidade.OESTE);
		
		Imovel imovel = new Imovel();
		imovel.setNomeImovel("KitNet Maiobão");
		imovel.setEnderecoImovel(endereco);
		imovel.setMetragem(new BigDecimal(55.2));
		imovel.setQuantidadeDomitorios(2);
		imovel.setQuantidadeBanheiros(1);
		imovel.setQuantidadeSuites(1);
		imovel.setVagasGaragem(1);
		imovel.setValorAluguelSugerido(new BigDecimal(750.2));
		imovel.setValorIPTU(new BigDecimal(76.5));
		imovel.setCliente(proprietario);
		imovel.setTipoImovel(TipoImovel.KITNET);
	
	
		ImovelService gImovel = new ImovelService();
		gImovel.adicionaOuAtualizaImovel(imovel);
		gImovel.closeRecursos();


		
		/**LocacaoImovel**/
		
		LocacaoImovelService glis = new LocacaoImovelService();
		Map<Cliente, List<LocacaoImovel>> locacoes = glis.listaTodasLocacoesPorCliente();
		
		ClienteService gcs02 = new ClienteService();
		Cliente cliente1 = gcs.buscaPorId(1);
		Cliente cliente2 = gcs.buscaPorId(2);
		
		System.out.println(locacoes.get(cliente1) + "\n\n" + locacoes.get(cliente2));
		
		gcs.closeRecursos();

		
		
		/**Aluguel**/
		
		//GestaoLocacaoImovelService glis = new GestaoLocacaoImovelService();
		//LocacaoImovel locacaoImovel = glis.buscaPorId(3);
	
		//Aluguel aluguel = new Aluguel();
		//aluguel.setDataPagamento(LocalDate.of(2019, 11, 25));
		//aluguel.setDataVencimento(LocalDate.of(2019, 11, 28));
		//aluguel.setValorPago(new BigDecimal(156.52));
		//aluguel.setLocacao(locacaoImovel);
		
		//GestaoAluguelService gla = new GestaoAluguelService();
		//gla.adicionaOuAtualizaAluguel(aluguel);
		//gla.closeRecursos();
		
	
		//Lista todos os alugueis de um determinado Inquilino

		ClienteService gcs03 = new ClienteService();
		Cliente cliente3 = gcs.buscaPorId(2);
		
		AluguelService gla = new AluguelService();
		List<Aluguel> alugueis = gla.listaTodosAlugueisDoInquilino(cliente1);
		
		for (Aluguel aluguel1 : alugueis) {
			System.out.println(aluguel1);
		}
		
		gla.closeRecursos();

		
//		Lista de totos os alugueis pagos de um determinado inquilino
		ClienteService gcs01 = new ClienteService();
		Cliente cliente4 = gcs.buscaPorId(1);

		AluguelService gla1 = new AluguelService();
		List<Aluguel> alugueisList = gla.listaTodosAlugueisDoInquilino(cliente1);

		for (Aluguel aluguel1 : alugueisList) {
			System.out.println(aluguel1);
		}
		
		gla.closeRecursos();


		AluguelService gla02 = new AluguelService();
		List<Aluguel> alugueisList2 = gla.listaDeTodosAlugueisPagosEmAtrasoNaDataDeVencimento();
		
		for (Aluguel aluguel1 : alugueisList2) {
			System.out.println(aluguel1);
		}
		
		gla.closeRecursos();
		

		 LocalDate hoje = LocalDate.now();
		 LocalDate outraData = LocalDate.of(2020, 07, 29);
		 
		 long diferencaEmDias = ChronoUnit.DAYS.between(hoje, outraData);
		 long diferencaEmAnos = ChronoUnit.YEARS.between(hoje, outraData);
		 
		 System.out.println(diferencaEmAnos + "\n" + diferencaEmDias);
		
		
		//Juros do aluguel atrasado
		

		AluguelService gla04 = new AluguelService();
		List<Aluguel> alugueisList3 = gla.listaDeTodosAlugueisPagosEmAtrasoNaDataDeVencimento();

		for (Aluguel aluguel1 : alugueisList3) {

			System.out.println(aluguel1.valorASerPagoComMulta());
		}

		gla.closeRecursos();
		

		
		
		
		

	}

}








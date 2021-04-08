package edu.ifma.locacaodeimoveis.builder.repository;


import edu.ifma.locacaodeimoveis.model.Aluguel;
import edu.ifma.locacaodeimoveis.model.Cliente;
import edu.ifma.locacaodeimoveis.service.AluguelService;
import edu.ifma.locacaodeimoveis.service.ClienteService;
import edu.ifma.locacaodeimoveis.service.NegocioException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

public class AluguelTest {


	private static ClienteService clienteService;
	private static AluguelService aluguelService;


	@BeforeClass
	public static void antes() {
		aluguelService = new AluguelService();
		clienteService = new ClienteService();
	}
	
	@AfterClass
	public static void depois() {
		aluguelService.closeRecursos();
	}
	
	@Test
	public void listaTodosAlugueisDoInquilino() {

		List<Aluguel> alugueis = new ArrayList<>();

		try {

			Cliente cliente = clienteService.buscaPorId(2);

			alugueis = aluguelService.listaTodosAlugueisDoInquilino(cliente);

		} catch (NegocioException e) {
			e.printStackTrace();
		}
		Assert.assertFalse(alugueis.isEmpty());
	}
	
	@Test
	public void listaTodosAlugueisPagosPorUmDeterminadoCliente() {
		
		try {
			
			Cliente cliente = clienteService.buscaPorId(1);
			
			for (Aluguel aluguel : aluguelService.listaDeTodosAlugueisPagosEmAtrasoNaDataDeVencimento()) {
				System.out.println(aluguel);
			}
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void listaTodosAlugueisEmAtrasoDaDataVencimento() {
		
		try {
			
			for (Aluguel aluguel : aluguelService.listaDeTodosAlugueisPagosEmAtrasoNaDataDeVencimento()) {
				System.out.println(aluguel);
			}
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void listeAlugueisEmAtrasoComValorAluguelComMulta() {
		
		try {
			
			for (Aluguel aluguel : aluguelService.listaTodosOsAlugueis()) {
				System.out.println(aluguel.getId() + " - R$ " + aluguel.valorASerPagoComMulta());
			}
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		
	}

	
}

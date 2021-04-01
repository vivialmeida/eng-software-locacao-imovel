package edu.ifma.locacaodeimoveis.builder.repository;


import edu.ifma.locacaodeimoveis.model.Aluguel;
import edu.ifma.locacaodeimoveis.model.Cliente;
import edu.ifma.locacaodeimoveis.service.GestaoAluguelService;
import edu.ifma.locacaodeimoveis.service.GestaoClienteService;
import edu.ifma.locacaodeimoveis.service.NegocioException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

import java.math.BigDecimal;

public class AluguelRepositoryTest {

	private static GestaoAluguelService aluguelService;
	private static GestaoClienteService clienteService;
	
	@BeforeClass
	public static void antes() {
		aluguelService = new GestaoAluguelService();
		clienteService = new GestaoClienteService();
	}
	
	@AfterClass
	public static void depois() {
		aluguelService.closeRecursos();
	}
	
	@Test
	public void listaTodosAlugueisDoInquilino() {
		try {
			
			Cliente cliente = clienteService.buscaPorId(2);
			
			for (Aluguel aluguel : aluguelService.listaTodosAlugueisDoInquilino(cliente)) {
				System.out.println(aluguel);
			}
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void listaTodosAlugueisPagosPorUmDeterminadoCliente() {
		
		try {
			
			Cliente cliente = clienteService.buscaPorId(1);
			
			for (Aluguel aluguel : aluguelService.listaTodosAlugueisPagosDoInquilino(cliente)) {
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

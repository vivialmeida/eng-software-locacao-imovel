package edu.ifma.locacaodeimoveis.service;

import edu.ifma.locacaodeimoveis.builder.AluguelBuilder;
import edu.ifma.locacaodeimoveis.builder.LocacaoBuilder;
import edu.ifma.locacaodeimoveis.model.Aluguel;
import edu.ifma.locacaodeimoveis.model.Cliente;
import edu.ifma.locacaodeimoveis.model.LocacaoImovel;
import edu.ifma.locacaodeimoveis.repository.AluguelRepository;
import edu.ifma.locacaodeimoveis.repository.LocacaoImovelRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class AluguelServiceTest {
  @Mock
  AluguelRepository repositorio;
  @Mock
  LocacaoImovelService locacaoImovelService;
  @InjectMocks
  AluguelService aluguelService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testAdicionaOuAtualizaAluguel() throws Exception {
    when(locacaoImovelService.buscaPorId(anyInt())).thenReturn(new LocacaoImovel());

    aluguelService.adicionaOuAtualizaAluguel(new Aluguel());
  }

  @Test
  public void testExluiAluguel() throws Exception {
    aluguelService.exluiAluguel(Integer.valueOf(0));
  }

  @Test
  public void testListaTodosOsAlugueis() throws Exception {
    when(locacaoImovelService.listaObjetos()).thenReturn((List<LocacaoImovel>) Arrays.asList(LocacaoBuilder.umaLocacao().constroi()));

    List<Aluguel> result = aluguelService.listaTodosOsAlugueis();
    Assert.assertEquals(Arrays.<Aluguel>asList(new Aluguel()), result);
  }

  @Test
  public void testBuscaPorId() throws Exception {
    when(locacaoImovelService.buscaPorId(anyInt())).thenReturn(LocacaoBuilder.umaLocacao().constroi());

    Aluguel result = aluguelService.buscaPorId(Integer.valueOf(0));
    Assert.assertEquals(new Aluguel(), result);
  }

  @Test
  public void testListaTodosAlugueisDoInquilino() throws Exception {
    when(repositorio.listaTodosAlugueisDoInquilino(any())).thenReturn(Arrays.<Aluguel>asList(new Aluguel()));

    List<Aluguel> result = aluguelService.listaTodosAlugueisDoInquilino(new Cliente());
    Assert.assertEquals(Arrays.<Aluguel>asList(new Aluguel()), result);
  }

  @Test
  public void testListaDeTodosAlugueisPagosEmAtrasoNaDataDeVencimento() throws Exception {
    when(repositorio.listaDeTodosAlugueisPagosEmAtrasoNaDataDeVencimento()).thenReturn(Arrays.<Aluguel>asList(new Aluguel()));

    List<Aluguel> result = aluguelService.listaDeTodosAlugueisPagosEmAtrasoNaDataDeVencimento();
    Assert.assertEquals(Arrays.<Aluguel>asList(new Aluguel()), result);
  }

  @Test
  public void testNotificaUsuariosEmAtraso() throws Exception {
    when(repositorio.emAtraso()).thenReturn(Arrays.<Aluguel>asList(new Aluguel()));

    aluguelService.notificaUsuariosEmAtraso();
  }

}


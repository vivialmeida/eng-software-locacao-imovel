package edu.ifma.locacaodeimoveis.service;

import edu.ifma.locacaodeimoveis.builder.LocacaoBuilder;
import edu.ifma.locacaodeimoveis.model.Cliente;
import edu.ifma.locacaodeimoveis.model.LocacaoImovel;
import edu.ifma.locacaodeimoveis.repository.LocacaoImovelRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class LocacaoImovelServiceTest {
  @Mock
  EntityManager MANAGER;
  @Mock
  LocacaoImovelRepository repositorio;
  @InjectMocks
  LocacaoImovelService locacaoImovelService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    locacaoImovelService.setFields(MANAGER, repositorio);
  }

  @Test
  public void testAdicionaOuAtualizaLocaoImovel() throws Exception {
    locacaoImovelService.adicionaOuAtualizaLocaoImovel(new LocacaoImovel());
    verify(repositorio).salva(any());
  }

  @Test
  public void testExcluiLocacaoImovel() throws Exception {
    locacaoImovelService.excluiLocacaoImovel(Integer.valueOf(0));
    verify(repositorio).exclui(any());
  }

  @Test
  public void testListaTodasAsLocacoesImoveis() throws Exception {
    when(repositorio.lista()).thenReturn(Arrays.asList(LocacaoBuilder.umaLocacao().constroi()));
    List<LocacaoImovel> result = locacaoImovelService.listaTodasAsLocacoesImoveis();
    Assert.assertEquals(Arrays.asList(LocacaoBuilder.umaLocacao().constroi()), result);
    Assert.assertNotNull(result);
  }

  @Test
  public void testBuscaPorId() throws Exception {
    when(repositorio.buscaPorId(anyInt())).thenReturn(new LocacaoImovel());
    LocacaoImovel result = locacaoImovelService.buscaPorId(Integer.valueOf(0));
    Assert.assertEquals(new LocacaoImovel(), result);
  }

  @Test
  public void testListaTodasLocacoesDoCliente() throws Exception {
    when(repositorio.listaTodasLocacoesDoCliente(any())).thenReturn(Arrays.<LocacaoImovel>asList(new LocacaoImovel()));
    List<LocacaoImovel> result = locacaoImovelService.listaTodasLocacoesDoCliente(new Cliente());
    Assert.assertEquals(Arrays.<LocacaoImovel>asList(new LocacaoImovel()), result);
  }


}


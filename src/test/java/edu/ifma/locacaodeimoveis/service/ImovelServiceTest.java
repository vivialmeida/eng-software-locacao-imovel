package edu.ifma.locacaodeimoveis.service;

import edu.ifma.locacaodeimoveis.model.Imovel;
import edu.ifma.locacaodeimoveis.model.TipoImovel;
import edu.ifma.locacaodeimoveis.repository.ImovelRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ImovelServiceTest {
  @Mock
  EntityManager MANAGER;
  @Mock
  ImovelRepository repositorio;
  @InjectMocks
  ImovelService imovelService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testAdicionaOuAtualizaImovel() throws Exception {
    imovelService.adicionaOuAtualizaImovel(new Imovel());
  }

  @Test
  public void testExcluiImovel() throws Exception {
    imovelService.excluiImovel(Integer.valueOf(0));
  }

  @Test
  public void testListaTodosOsImoveis() throws Exception {
    List<Imovel> result = imovelService.listaTodosOsImoveis();
    Assert.assertEquals(Arrays.<Imovel>asList(new Imovel()), result);
  }

  @Test
  public void testBuscaPorId() throws Exception {
    Imovel result = imovelService.buscaPorId(Integer.valueOf(0));
    Assert.assertEquals(new Imovel(), result);
  }

  @Test
  public void testListaDeImoveisAbaixoDoValor() throws Exception {
    when(repositorio.listaDeImoveisAbaixoDoValor(any())).thenReturn(Arrays.<Imovel>asList(new Imovel()));

    List<Imovel> result = imovelService.listaDeImoveisAbaixoDoValor(new BigDecimal(0));
    Assert.assertEquals(Arrays.<Imovel>asList(new Imovel()), result);
  }

  @Test
  public void testListaDeImoveisPorTipo() throws Exception {
    when(repositorio.listaDeImoveisPorTipo(any())).thenReturn(Arrays.<Imovel>asList(new Imovel()));

    List<Imovel> result = imovelService.listaDeImoveisPorTipo(TipoImovel.APARTAMENTO);
    Assert.assertEquals(Arrays.<Imovel>asList(new Imovel()), result);
  }

}


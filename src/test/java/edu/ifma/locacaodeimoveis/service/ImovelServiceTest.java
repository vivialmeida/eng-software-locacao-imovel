package edu.ifma.locacaodeimoveis.service;

import edu.ifma.locacaodeimoveis.builder.ImovelBuilder;
import edu.ifma.locacaodeimoveis.model.Imovel;
import edu.ifma.locacaodeimoveis.model.TipoImovel;
import edu.ifma.locacaodeimoveis.repository.ImovelRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
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
   imovelService.setManager(MANAGER);
   imovelService.setRepositorio(repositorio);
  }

  @Test
  public void testAdiciona() throws Exception {
    imovelService.adicionaOuAtualizaImovel(ImovelBuilder.umImovel().constroi());
    verify(repositorio).salva(any());
  }

  @Test
  public void testAtualiza() throws Exception {
    imovelService.adicionaOuAtualizaImovel(ImovelBuilder.umImovel().comId(2).constroi());
    verify(repositorio).atualiza(any());
  }

  @Test
  public void testExcluiImovel() throws Exception {
    imovelService.excluiImovel(Integer.valueOf(0));
    verify(repositorio).exclui(any());
  }

  @Test
  public void testListaTodosOsImoveis() throws Exception {
    List<Imovel> result = imovelService.listaTodosOsImoveis();
    Assert.assertNotNull(result);
  }

  @Test
  public void testBuscaPorId() throws Exception {
    when(repositorio.buscaPorId(any())).thenReturn(new Imovel());
    Imovel result = imovelService.buscaPorId(Integer.valueOf(0));
    Assert.assertEquals(new Imovel(), result);
  }

  @Test
  public void testListaDeImoveisAbaixoDoValor() throws Exception {
    when(repositorio.listaDeImoveisAbaixoDoValor(new BigDecimal(1000))).thenReturn(Arrays.<Imovel>asList(ImovelBuilder.umImovel().constroi()));

    List<Imovel> result = imovelService.listaDeImoveisAbaixoDoValor(new BigDecimal(1000));
    Assert.assertEquals(Arrays.<Imovel>asList(ImovelBuilder.umImovel().constroi()), result);
    Assert.assertEquals(result.size(), 1);
    Assert.assertEquals(result.get(0).getValorAluguelSugerido(), new BigDecimal(800));
  }

  @Test
  public void testListaDeImoveisPorTipo() throws Exception {
    Imovel imovel = ImovelBuilder.umImovel().comTipo(TipoImovel.APARTAMENTO).constroi();
    Imovel imovel2 = ImovelBuilder.umImovel().comTipo(TipoImovel.APARTAMENTO).constroi();
    List apartamentos = Arrays.asList(imovel, imovel2);

    when(repositorio.listaDeImoveisPorTipo(any())).thenReturn(apartamentos);

    List<Imovel> result = imovelService.listaDeImoveisPorTipo(TipoImovel.APARTAMENTO);
    Assert.assertSame(apartamentos, result);
    Assert.assertEquals(apartamentos.size(), 2);

  }


}


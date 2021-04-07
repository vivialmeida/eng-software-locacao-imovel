package edu.ifma.locacaodeimoveis.builder.repository;

import edu.ifma.locacaodeimoveis.model.*;
import edu.ifma.locacaodeimoveis.service.ClienteService;
import edu.ifma.locacaodeimoveis.service.ImovelService;
import edu.ifma.locacaodeimoveis.service.NegocioException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ImovelTest {

  private static ImovelService imovelService;
  private static ClienteService clienteService;

  @BeforeClass
  public static void antes() {
    imovelService = new ImovelService();
    clienteService = new ClienteService();
  }

  @AfterClass
  public static void depois() {
    imovelService.closeRecursos();
  }

  @Test
  public void adicionaOuAtualiza() {

    try {

      Cliente proprietario = clienteService.buscaPorId(2);
      EnderecoImovel endereco = new EnderecoImovel();
      endereco.setBairro("Cidade Operaria 2");
      endereco.setCep("65000-000");
      endereco.setLogradouro("Rua 25");
      endereco.setPontoDeReferencia("Proxima ao Salão Estilo");
      endereco.setZonaCidade(ZonaCidade.OESTE);

      Imovel imovel = new Imovel();
      imovel.setNomeImovel("KitNet CidadeOperaria");
      imovel.setEnderecoImovel(endereco);
      imovel.setMetragem(new BigDecimal(99.99));
      imovel.setQuantidadeDomitorios(5);
      imovel.setQuantidadeBanheiros(5);
      imovel.setQuantidadeSuites(5);
      imovel.setVagasGaragem(5);
      imovel.setValorAluguelSugerido(new BigDecimal(999.9));
      imovel.setValorIPTU(new BigDecimal(99.99));
      imovel.setTipoImovel(TipoImovel.KITNET);

      imovelService.adicionaOuAtualizaImovel(imovel);

    } catch (NegocioException e) {

      e.printStackTrace();

    }

  }

  @Test
  public void exlui() {

    try {

      imovelService.excluiImovel(5);

    } catch (NegocioException e) {

      e.printStackTrace();

    }
  }

  @Test
  public void buscaImovelPeloId() {

    try {

      Imovel imovel = imovelService.buscaPorId(5);
      Assert.assertThat(5, is(equalTo(imovel.getId())));

    } catch (NegocioException e) {

      e.printStackTrace();

    }
  }

  @Test
  public void listaTodosOsImoveis() {

    try {

      imovelService.listaTodosOsImoveis();

    } catch (NegocioException e) {

      e.printStackTrace();

    }

  }

  @Test
  public void listaDeImoveisAbaixoDoValor() {

    try {

      for (Imovel imovel : imovelService.listaDeImoveisAbaixoDoValor(new BigDecimal(152))) {
        System.out.println(imovel);
      }

    } catch (NegocioException e) {

      e.printStackTrace();

    }

  }

  @Test
  public void listaDeImoveisPorTipoApartamento() {

	  List<Imovel> imovelList = new ArrayList<>();

	  try {

    imovelList = imovelService.listaDeImoveisPorTipo(TipoImovel.APARTAMENTO);
    } catch (NegocioException ex) {
      ex.printStackTrace();
    }

	  Assert.assertTrue(imovelList.size() >0 );

  }


}

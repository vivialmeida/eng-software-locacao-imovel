package edu.ifma.locacaodeimoveis.repository;

import edu.ifma.locacaodeimoveis.service.LocacaoImovelService;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class LocacaoTest {

  private static LocacaoImovelService locacaoService;


  @BeforeClass
  public static void antes() {
    locacaoService = new LocacaoImovelService();
  }

  @AfterClass
  public static void depois() {
    locacaoService.closeRecursos();
  }


}

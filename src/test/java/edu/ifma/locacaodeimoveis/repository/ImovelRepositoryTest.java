package edu.ifma.locacaodeimoveis.repository;

import edu.ifma.locacaodeimoveis.builder.ClienteBuilder;
import edu.ifma.locacaodeimoveis.builder.ImovelBuilder;
import edu.ifma.locacaodeimoveis.model.Cliente;
import edu.ifma.locacaodeimoveis.model.Imovel;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ImovelRepositoryTest {

  private EntityManager manager;
  private static EntityManagerFactory emf;
  private ImovelRepository imovelRepository;
  private ClienteRepository clienteRepository;


  @BeforeClass
  public static void inicio() {
    emf = Persistence.createEntityManagerFactory("locadoraPU_test");
  }

  @Before
  public void antes() {
    manager = emf.createEntityManager();
    manager.getTransaction().begin();
    imovelRepository = new ImovelRepository(manager);
//    locacaoImovelRepository  = new LocacaoImovelRepository(manager);
    clienteRepository  = new ClienteRepository(manager);

  }
  @AfterEach
  public void depois() {
    manager.getTransaction().rollback();
  }

  @AfterAll
  public static void fim() {
    emf.close();
  }

  @Test
  public void testSalva() throws Exception {
    Cliente cliente = ClienteBuilder.umCliente().constroi();
    Imovel imovel = ImovelBuilder.umImovel().constroi();
    try {
      clienteRepository.salva(cliente);
      imovelRepository.salva(imovel);

    } catch (Exception e) {
      e.printStackTrace();
    }

//    Assert.assertTrue(aluguelRepository.lista().size() ==1);

  }
}

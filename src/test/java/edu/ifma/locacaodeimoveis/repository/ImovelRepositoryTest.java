package edu.ifma.locacaodeimoveis.repository;

import edu.ifma.locacaodeimoveis.builder.ClienteBuilder;
import edu.ifma.locacaodeimoveis.builder.ImovelBuilder;
import edu.ifma.locacaodeimoveis.model.Aluguel;
import edu.ifma.locacaodeimoveis.model.Cliente;
import edu.ifma.locacaodeimoveis.model.Imovel;
import edu.ifma.locacaodeimoveis.model.TipoImovel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

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
      imovel.setCliente(cliente);
      imovelRepository.salva(imovel);

    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertTrue(imovelRepository.lista().size() ==1);

  }

  @Test
  public void testRecuperaImovelPorTipo() throws Exception {
    Cliente cliente = ClienteBuilder.umCliente().constroi();
    Imovel imovel1 = ImovelBuilder.umImovel().comTipo(TipoImovel.APARTAMENTO).constroi();
    Imovel imovel2 = ImovelBuilder.umImovel().comTipo(TipoImovel.APARTAMENTO).constroi();
    Imovel imovel3 = ImovelBuilder.umImovel().comTipo(TipoImovel.KITNET).constroi();


    try {
      clienteRepository.salva(cliente);
      imovel1.setCliente(cliente);
      imovel2.setCliente(cliente);
      imovel3.setCliente(cliente);
      imovelRepository.salva(imovel1);
      imovelRepository.salva(imovel2);
      imovelRepository.salva(imovel3);


    } catch (Exception e) {
      e.printStackTrace();
    }

    List<Imovel>imoveisApartamento = imovelRepository.listaDeImoveisPorTipo(TipoImovel.APARTAMENTO);
    Assert.assertTrue(imoveisApartamento.size() == 2);

  }

  @Test
  public void testRecuperaImovelAbaixoDoValor() throws Exception {
    Cliente cliente = ClienteBuilder.umCliente().constroi();
    Imovel imovel1 = ImovelBuilder.umImovel().comValor(new BigDecimal(850)).constroi();
    Imovel imovel2 = ImovelBuilder.umImovel().comValor(new BigDecimal(1500)).constroi();
    Imovel imovel3 = ImovelBuilder.umImovel().comValor(new BigDecimal(1100)).constroi();


    try {
      clienteRepository.salva(cliente);
      imovel1.setCliente(cliente);
      imovel2.setCliente(cliente);
      imovel3.setCliente(cliente);
      imovelRepository.salva(imovel1);
      imovelRepository.salva(imovel2);
      imovelRepository.salva(imovel3);


    } catch (Exception e) {
      e.printStackTrace();
    }

    List<Imovel> imoveisApartamento = imovelRepository.listaDeImoveisAbaixoDoValor(new BigDecimal(1000));
    Assert.assertTrue(imoveisApartamento.size() == 1);

  }
}

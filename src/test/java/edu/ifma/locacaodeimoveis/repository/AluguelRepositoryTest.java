package edu.ifma.locacaodeimoveis.repository;

import edu.ifma.locacaodeimoveis.builder.AluguelBuilder;
import edu.ifma.locacaodeimoveis.builder.ClienteBuilder;
import edu.ifma.locacaodeimoveis.builder.ImovelBuilder;
import edu.ifma.locacaodeimoveis.builder.LocacaoBuilder;
import edu.ifma.locacaodeimoveis.model.Aluguel;
import edu.ifma.locacaodeimoveis.model.Cliente;
import edu.ifma.locacaodeimoveis.model.Imovel;
import edu.ifma.locacaodeimoveis.model.LocacaoImovel;
import org.hamcrest.CoreMatchers;
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
import java.math.RoundingMode;
import java.time.LocalDate;

public class AluguelRepositoryTest {

  private EntityManager manager;
  private static EntityManagerFactory emf;
  private AluguelRepository aluguelRepository;
  private LocacaoImovelRepository locacaoImovelRepository;
  private ClienteRepository clienteRepository;
  private ImovelRepository imovelRepository;


  @BeforeClass
  public static void inicio() {
    emf = Persistence.createEntityManagerFactory("locadoraPU_test");
  }

  @Before
  public void antes() {
    manager = emf.createEntityManager();
    manager.getTransaction().begin();
    aluguelRepository = new AluguelRepository(manager);
    locacaoImovelRepository  = new LocacaoImovelRepository(manager);
    clienteRepository  = new ClienteRepository(manager);
    imovelRepository = new ImovelRepository(manager);

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
    Imovel imovel = ImovelBuilder.umImovel().constroi();
    Cliente cliente = ClienteBuilder.umCliente().constroi();
    LocacaoImovel locacaoImovel = LocacaoBuilder.umaLocacao().paraUmCliente(cliente).paraUmImovel(imovel).constroi();
    Aluguel aluguel = AluguelBuilder.umAluguel().paraUmaLocacao(locacaoImovel).paraUmCliente(cliente.getNome()).constroi();
    aluguel.setLocacao(locacaoImovel);
    imovel.setCliente(cliente);

    try {
      clienteRepository.salva(cliente);
      imovelRepository.salva(imovel);
      locacaoImovelRepository.salva(locacaoImovel);
      aluguelRepository.salva(aluguel);

    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertTrue(aluguelRepository.lista().size() ==1);
    Assert.assertSame(locacaoImovel.getCliente().getNome(), "Akila");
    Assert.assertEquals(aluguel.getLocacao(), locacaoImovel);

  }

  @Test
  public void testAtualiza() throws Exception {
    Imovel imovel = ImovelBuilder.umImovel().constroi();
    Cliente cliente = ClienteBuilder.umCliente().constroi();
    LocacaoImovel locacaoImovel = LocacaoBuilder.umaLocacao().paraUmCliente(cliente).paraUmImovel(imovel).constroi();
    Aluguel aluguel = AluguelBuilder.umAluguel().paraUmaLocacao(locacaoImovel).paraUmCliente(cliente.getNome()).constroi();
    aluguel.setLocacao(locacaoImovel);
    imovel.setCliente(cliente);

     //PERSISTINDO

    try {
      clienteRepository.salva(cliente);
      imovelRepository.salva(imovel);
      locacaoImovelRepository.salva(locacaoImovel);
      aluguelRepository.salva(aluguel);

    } catch (Exception e) {
      e.printStackTrace();
    }

    //ATUALIZANDO

    cliente.setNome("Viviane");
    locacaoImovel.setDiaVencimento(15);
    aluguel.setValorPago(new BigDecimal(500));


    try {
      clienteRepository.atualiza(cliente);
      locacaoImovelRepository.atualiza(locacaoImovel);
      aluguelRepository.atualiza(aluguel);

    } catch (Exception e) {
      e.printStackTrace();
    }

    //RECUPERANDO

    Cliente cliente1 =clienteRepository.buscaPorId(cliente.getId());

    Assert.assertEquals(cliente1.getNome(), "Viviane");
    Assert.assertThat(new BigDecimal(500), CoreMatchers.is(aluguel.getValorPago()));

  }


  @Test
  public void testAluguelComMulta() throws Exception {

    Aluguel aluguel = AluguelBuilder.umAluguel().constroi();
    aluguel.setDataVencimento(LocalDate.of(2021, 02, 10));
    aluguel.setDataPagamento(LocalDate.of(2021, 03, 05));

    Assert.assertNotSame(aluguel.valorASerPagoComMulta(),(aluguel.getLocacao().getValorAluguel()));
    Assert.assertEquals(aluguel.valorASerPagoComMulta(), new BigDecimal(575.90).setScale(2, RoundingMode.HALF_UP));

  }
}

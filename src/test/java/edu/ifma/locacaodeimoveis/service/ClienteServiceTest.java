package edu.ifma.locacaodeimoveis.service;

import edu.ifma.locacaodeimoveis.model.Cliente;
import edu.ifma.locacaodeimoveis.repository.ClienteRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ClienteServiceTest {
  @Mock
  EntityManager MANAGER;
  @Mock
  ClienteRepository repositorio;
  @InjectMocks
  ClienteService clienteService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testAdicionaOuAtualizaCliente() throws Exception {
    clienteService.adicionaOuAtualizaCliente(new Cliente());
  }

  @Test
  public void testExluiCliente() throws Exception {
    clienteService.exluiCliente(Integer.valueOf(0));
  }

  @Test
  public void testListaTodosOsClientes() throws Exception {
    List<Cliente> result = clienteService.listaTodosOsClientes();
    Assert.assertEquals(Arrays.<Cliente>asList(new Cliente()), result);
  }

  @Test
  public void testBuscaPorId() throws Exception {
    Cliente result = clienteService.buscaPorId(Integer.valueOf(0));
    Assert.assertEquals(new Cliente(), result);
  }

}


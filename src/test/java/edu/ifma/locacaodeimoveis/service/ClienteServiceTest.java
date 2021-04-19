package edu.ifma.locacaodeimoveis.service;

import edu.ifma.locacaodeimoveis.builder.ClienteBuilder;
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
    clienteService.serFields(MANAGER, repositorio);
  }

  @Test
  public void testAdicionaOuAtualizaCliente() throws Exception {
    clienteService.adicionaOuAtualizaCliente(new Cliente());
    verify(repositorio).salva(any());
  }

  @Test
  public void testExluiCliente() throws Exception {
    clienteService.exluiCliente(Integer.valueOf(0));
    verify(repositorio).exclui(any());

  }

  @Test
  public void testListaTodosOsClientes() throws Exception {
    when(repositorio.lista()).thenReturn(Arrays.asList(new Cliente()));
    List<Cliente> result = clienteService.listaTodosOsClientes();
    Assert.assertEquals(Arrays.<Cliente>asList(new Cliente()), result);
    Assert.assertTrue(result.size() == 1);

  }

  @Test
  public void testBuscaPorId() throws Exception {
    when(repositorio.buscaPorId(anyInt())).thenReturn(ClienteBuilder.umCliente().constroi());
    Cliente result = clienteService.buscaPorId(Integer.valueOf(0));
    Assert.assertEquals(ClienteBuilder.umCliente().constroi(), result);

  }

}


package edu.ifma.locacaodeimoveis.repository;

import edu.ifma.locacaodeimoveis.builder.ClienteBuilder;
import edu.ifma.locacaodeimoveis.model.Aluguel;
import edu.ifma.locacaodeimoveis.model.Cliente;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ClienteServiceTest {
    @Autowired
    private ClienteRepository clienteRepository;
    private Cliente cliente;

    @Before
    public void setUp() {
        clienteRepository = mock(ClienteRepository.class);
        cliente = ClienteBuilder.umCliente().comId(1L).constroi();
    }

    @Test
    public void saveComNomeNuloDeveLancarExcecao() {
        when(clienteRepository.save(any())).thenThrow(ConstraintViolationException.class);
        ConstraintViolationException exception = Assertions
                .assertThrows(ConstraintViolationException.class,
                        () -> {
                            cliente.setNomeCliente(null);
                            clienteRepository.save(cliente);
                        },
                        "Deveria Lancar ConstraintViolationException");
        Assertions.assertNotNull(exception);
    }

    @Test
    public void saveComCpfNuloDeveLancarExcecao() {
        when(clienteRepository.save(any())).thenThrow(ConstraintViolationException.class);
        ConstraintViolationException exception = Assertions
                .assertThrows(ConstraintViolationException.class,
                        () -> {
                            cliente.setCpf(null);
                            clienteRepository.save(cliente);
                        },
                        "Deveria Lancar ConstraintViolationException");
        Assertions.assertNotNull(exception);
    }

    @Test
    public void saveComCelularNuloDeveLancarExcecao() {
        when(clienteRepository.save(any())).thenThrow(ConstraintViolationException.class);
        ConstraintViolationException exception = Assertions
                .assertThrows(ConstraintViolationException.class,
                        () -> {
                            cliente.setCelular(null);
                            clienteRepository.save(cliente);
                        },
                        "Deveria Lancar ConstraintViolationException");
        Assertions.assertNotNull(exception);
    }

    @Test
    public void saveComEmailNuloDeveLancarExcecao() {
        when(clienteRepository.save(any())).thenThrow(ConstraintViolationException.class);
        ConstraintViolationException exception = Assertions
                .assertThrows(ConstraintViolationException.class,
                        () -> {
                            cliente.setEmail(null);
                            clienteRepository.save(cliente);
                        },
                        "Deveria Lancar ConstraintViolationException");
        Assertions.assertNotNull(exception);
    }

    @Test
    public void saveComDtNascimentoNuloDeveLancarExcecao() {
        when(clienteRepository.save(any())).thenThrow(ConstraintViolationException.class);
        ConstraintViolationException exception = Assertions
                .assertThrows(ConstraintViolationException.class,
                        () -> {
                            cliente.setDtNascimento(null);
                            clienteRepository.save(cliente);
                        },
                        "Deveria Lancar ConstraintViolationException");
        Assertions.assertNotNull(exception);
    }

    @Test
    public void deveSalvarCliente() {
        when(clienteRepository.save(any())).thenReturn(cliente);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        assertNotNull(clienteSalvo);
    }

    @Test
    public void deveRemoverCliente() {
        when(clienteRepository.save(any())).thenReturn(cliente);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        clienteRepository.deleteById(clienteSalvo.getIdCliente());
        Optional<Cliente> byId = clienteRepository.findById(clienteSalvo.getIdCliente());
        assertFalse(byId.isPresent());
    }
}

package edu.ifma.locacaodeimoveis.repository;

import edu.ifma.locacaodeimoveis.builder.ImovelBuilder;
import edu.ifma.locacaodeimoveis.model.Cliente;
import edu.ifma.locacaodeimoveis.model.Imovel;
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
public class ImovelServiceTest {
    @Autowired
    private ImovelRepository imovelRepository;
    private Imovel imovel;

    @Before
    public void setUp() {
        imovelRepository = mock(ImovelRepository.class);
        imovel = ImovelBuilder.umImovel().comId(1L).constroi();
    }

    @Test
    public void saveComNomeNuloDeveLancaException() {
        when(imovelRepository.save(any())).thenThrow(ConstraintViolationException.class);
        ConstraintViolationException exception = Assertions
                .assertThrows(ConstraintViolationException.class,
                        () -> {
                            imovel.setNome(null);
                            imovelRepository.save(imovel);
                        },
                        "Deveria Lancar ConstraintViolationException");
        Assertions.assertNotNull(exception);
    }

    @Test
    public void saveComEnderecoNuloDeveLancaException() {
        when(imovelRepository.save(any())).thenThrow(ConstraintViolationException.class);
        ConstraintViolationException exception = Assertions
                .assertThrows(ConstraintViolationException.class,
                        () -> {
                            imovel.setEndereco(null);
                            imovelRepository.save(imovel);
                        },
                        "Deveria Lancar ConstraintViolationException");
        Assertions.assertNotNull(exception);
    }

    @Test
    public void saveComBairroNuloDeveLancaException() {
        when(imovelRepository.save(any())).thenThrow(ConstraintViolationException.class);
        ConstraintViolationException exception = Assertions
                .assertThrows(ConstraintViolationException.class,
                        () -> {
                            imovel.setBairro(null);
                            imovelRepository.save(imovel);
                        },
                        "Deveria Lancar ConstraintViolationException");
        Assertions.assertNotNull(exception);
    }

    @Test
    public void deveSalvarImovel() {
        when(imovelRepository.save(any())).thenReturn(imovel);
        Imovel imovelSalvo = imovelRepository.save(imovel);
        assertNotNull(imovelSalvo);
    }

    @Test
    public void deveRemoverImovel() {
        when(imovelRepository.save(any())).thenReturn(imovel);
        Imovel imovelSalvo = imovelRepository.save(imovel);
        imovelRepository.deleteById(imovelSalvo.getIdImovel());
        Optional<Imovel> byId = imovelRepository.findById(imovelSalvo.getIdImovel());
        assertFalse(byId.isPresent());
    }
}

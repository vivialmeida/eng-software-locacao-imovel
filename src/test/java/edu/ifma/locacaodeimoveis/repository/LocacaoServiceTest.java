package edu.ifma.locacaodeimoveis.repository;

import edu.ifma.locacaodeimoveis.builder.LocacaoBuilder;
import edu.ifma.locacaodeimoveis.model.Imovel;
import edu.ifma.locacaodeimoveis.model.Locacao;
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
public class LocacaoServiceTest {
    @Autowired
    private LocacaoRepository locacaoRepository;
    private Locacao locacao;

    @Before
    public void setUp() {
        locacaoRepository = mock(LocacaoRepository.class);
        locacao = LocacaoBuilder.umaLocacao().comId(1L).constroi();
    }

    @Test
    public void saveComValorAluguelNuloDeveLancaException() {
        when(locacaoRepository.save(any())).thenThrow(ConstraintViolationException.class);
        ConstraintViolationException exception = Assertions
                .assertThrows(ConstraintViolationException.class,
                        () -> {
                            locacao.setValorAluguel(null);
                            locacaoRepository.save(locacao);
                        },
                        "Deveria Lancar ConstraintViolationException");
        Assertions.assertNotNull(exception);
    }

    @Test
    public void saveComDataInicioNuloDeveLancaException() {
        when(locacaoRepository.save(any())).thenThrow(ConstraintViolationException.class);
        ConstraintViolationException exception = Assertions
                .assertThrows(ConstraintViolationException.class,
                        () -> {
                            locacao.setDataInicio(null);
                            locacaoRepository.save(locacao);
                        },
                        "Deveria Lancar ConstraintViolationException");
        Assertions.assertNotNull(exception);
    }

    @Test
    public void saveComDataFimNuloDeveLancaException() {
        when(locacaoRepository.save(any())).thenThrow(ConstraintViolationException.class);
        ConstraintViolationException exception = Assertions
                .assertThrows(ConstraintViolationException.class,
                        () -> {
                            locacao.setDataFim(null);
                            locacaoRepository.save(locacao);
                        },
                        "Deveria Lancar ConstraintViolationException");
        Assertions.assertNotNull(exception);
    }

    @Test
    public void deveSalvarLocacao() {
        when(locacaoRepository.save(any())).thenReturn(locacao);
        Locacao locacaoSalva = locacaoRepository.save(locacao);
        assertNotNull(locacaoSalva);
    }

    @Test
    public void deveRemoverLocacao() {
        when(locacaoRepository.save(any())).thenReturn(locacao);
        Locacao locacaoSalva = locacaoRepository.save(locacao);
        locacaoRepository.deleteById(locacaoSalva.getIdLocacao());
        Optional<Locacao> byId = locacaoRepository.findById(locacaoSalva.getIdLocacao());
        assertFalse(byId.isPresent());
    }
}

package edu.ifma.locacaodeimoveis.repository;

import edu.ifma.locacaodeimoveis.builder.AluguelBuilder;
import edu.ifma.locacaodeimoveis.model.Aluguel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

public class AluguelServiceTest {
    @Autowired
    private AluguelRepository aluguelRepository;
    private Aluguel aluguel;

    @Before
    public void setUp() {
        aluguelRepository = mock(AluguelRepository.class);
        aluguel = AluguelBuilder.umAluguel()
                .comId(1L).constroi();
    }

    @Test
    public void saveComDataDeVencimentoNulaDeveLancaException() {
        when(aluguelRepository.save(any())).thenThrow(ConstraintViolationException.class);
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class,
                () -> {
                    aluguel.setDataDeVencimento(null);
                    aluguelRepository.save(aluguel);
                },
                "Deveria Lancar ConstraintViolationException");
        assertNotNull(exception);
    }

    @Test
    public void saveComValorPagoNuloDeveLancaException() {
        when(aluguelRepository.save(any())).thenThrow(ConstraintViolationException.class);
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class,
                () -> {
                    aluguel.setValorPago(null);
                    aluguelRepository.save(aluguel);
                },
                "Deveria Lancar ConstraintViolationException");
        assertNotNull(exception);
    }

    @Test
    public void saveComDataDePagamentoNuloDeveLancaException() {
        when(aluguelRepository.save(any())).thenThrow(ConstraintViolationException.class);
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class,
                () -> {
                    aluguel.setDataDePagamento(null);
                    aluguelRepository.save(aluguel);
                },
                "Deveria Lancar ConstraintViolationException");
        assertNotNull(exception);
    }

    @Test
    public void deveSalvarAluguel() {
        when(aluguelRepository.save(any())).thenReturn(aluguel);
        Aluguel aluguelSalvo = aluguelRepository.save(aluguel);
        assertNotNull(aluguelSalvo);
    }

    @Test
    public void deveRemoverAluguel() {
        when(aluguelRepository.save(any())).thenReturn(aluguel);
        Aluguel aluguelSalvo = aluguelRepository.save(aluguel);
        aluguelRepository.deleteById(aluguelSalvo.getIdAluguel());
        Optional<Aluguel> byId = aluguelRepository.findById(aluguelSalvo.getIdAluguel());
        assertFalse(byId.isPresent());
    }

    @Test
    public void deveCalcularValorAluguelComMulta() {

        Aluguel aluguel = AluguelBuilder.umAluguel().constroi();
        aluguel.setDataDePagamento(LocalDate.of(2020, 12, 14));
        BigDecimal valorComMulta = aluguel.valorASerPagoComMulta();
        Assert.assertEquals(new BigDecimal(506.60).setScale(2, RoundingMode.HALF_UP), valorComMulta);

    }


}

package edu.ifma.locacaodeimoveis.repository;

import edu.ifma.locacaodeimoveis.model.Aluguel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    @Query(value = "From Aluguel")
    List<Aluguel> todos(Sort sort);

    @Query(value = "From Aluguel a where a.dataDeVencimento = :dataDeVencimento")
    List<Aluguel> findBy(@Param("dataDeVencimento") LocalDate dataDeVencimento);

    @Query(value = "From Aluguel a where a.dataDePagamento is not null")
    List<Aluguel> buscarPagos();

    @Query(value = "From Aluguel a where a.dataDePagamento > a.dataDeVencimento" )
    List<Aluguel> buscarPagosAtrasado();
}

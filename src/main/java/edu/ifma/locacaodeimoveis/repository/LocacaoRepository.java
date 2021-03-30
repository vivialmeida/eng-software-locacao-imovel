package edu.ifma.locacaodeimoveis.repository;

import edu.ifma.locacaodeimoveis.model.Locacao;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
    @Query(value = "From Locacao")
    List<Locacao> todos(Sort sort);

    @Query(value = "From Locacao where inquilino.nomeCliente = :nome")
    Locacao findBy(@Param("nome") String nomeCliente);
}

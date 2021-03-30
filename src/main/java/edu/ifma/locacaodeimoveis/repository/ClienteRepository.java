package edu.ifma.locacaodeimoveis.repository;

import edu.ifma.locacaodeimoveis.model.Cliente;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query(value = "From Cliente")
    List<Cliente> todos(Sort sort);

    @Query(value = "From Cliente c where c.nomeCliente = :nome")
    List<Cliente> findBy(@Param("nome") String nome);
}

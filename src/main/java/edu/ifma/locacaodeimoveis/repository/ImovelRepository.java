package edu.ifma.locacaodeimoveis.repository;

import edu.ifma.locacaodeimoveis.model.Imovel;
import edu.ifma.locacaodeimoveis.model.TipoImovel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long> {
    @Query(value = "From Imovel")
    List<Imovel> todos(Sort sort);

    @Query(value = "From Imovel i inner join Locacao  l on  l.imovel.idImovel = i.idImovel " +
            "where i.bairro = :bairro and i.tipoImovel = :tipo  and l.ativo = true ")
    List<Imovel> findByTipoImovelAndBairro(@Param("tipo") TipoImovel tipo, @Param("bairro") String bairro);

    @Query(value = "From Imovel as i inner join Locacao as l on  l.imovel.idImovel = i.idImovel " +
            "where i.valorAluguelSugerido <= :valor " +
            "and l.ativo = true")
    List<Imovel> findByValorAluguelSugerido(@Param("valor") BigDecimal valor);
}

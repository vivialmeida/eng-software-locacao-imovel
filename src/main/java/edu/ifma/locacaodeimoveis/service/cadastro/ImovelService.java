package edu.ifma.locacaodeimoveis.service.cadastro;

import edu.ifma.locacaodeimoveis.model.Imovel;
import edu.ifma.locacaodeimoveis.model.TipoImovel;
import edu.ifma.locacaodeimoveis.repository.ImovelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    public Imovel inserirOuAlterar(Imovel imovel) {
        return imovelRepository.save(imovel);
    }

    public List<Imovel> buscarImoveis() {
        return imovelRepository.findAll();
    }

    public Imovel buscarPor(Long id) {
        return imovelRepository.getOne(id);
    }

    public List<Imovel> buscarPor(TipoImovel tipo, String bairro) {
        return imovelRepository.findByTipoImovelAndBairro(tipo, bairro);
    }

    public List<Imovel> buscarPorValor(BigDecimal valor) {
        return imovelRepository.findByValorAluguelSugerido(valor);
    }
}

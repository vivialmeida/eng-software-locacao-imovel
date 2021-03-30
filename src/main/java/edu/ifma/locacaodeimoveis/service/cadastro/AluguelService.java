package edu.ifma.locacaodeimoveis.service.cadastro;

import edu.ifma.locacaodeimoveis.model.Aluguel;
import edu.ifma.locacaodeimoveis.repository.AluguelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AluguelService {
    private final AluguelRepository aluguelRepository;

    public Aluguel inserirOuAlterar(Aluguel aluguel) {
        return aluguelRepository.save(aluguel);
    }

    public List<Aluguel> buscarAlugueis() {
        return aluguelRepository.findAll();
    }

    public List<Aluguel> buscarAlugueisPagos() {
        return aluguelRepository.buscarPagos();
    }
    public Aluguel bucarAluguel(Long idAluguel){
       return aluguelRepository.getOne(idAluguel);
    }



}

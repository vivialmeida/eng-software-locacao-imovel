package edu.ifma.locacaodeimoveis.service.cadastro;

import edu.ifma.locacaodeimoveis.model.Aluguel;
import edu.ifma.locacaodeimoveis.model.Locacao;
import edu.ifma.locacaodeimoveis.repository.LocacaoRepository;
import edu.ifma.locacaodeimoveis.service.exception.LocacaoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocacaoService {
      private final LocacaoRepository locacaoRepository;

      private final AluguelService aluguelService;

      public Locacao inserirOuAlterar(Locacao locacao) {
            return locacaoRepository.save(locacao);
      }

      public List<Locacao> buscarLocacoes() {
            return locacaoRepository.findAll();
      }

      public Locacao buscarLocacao(Long id) {
            return locacaoRepository.getOne(id);
      }

      public void pagarAluguel(long idLocacao, Aluguel aluguel) {
            Locacao locacao = buscarLocacao(idLocacao);
            if (locacao.getValorAluguel().compareTo(aluguel.getValorPago()) < 1) {
                  throw new LocacaoException("Valor do aluguel menor do que da locação !");
            }

            locacao.getAluguels().add(aluguel);
            aluguel.setLocacao(locacao);
            locacaoRepository.save(locacao);
            aluguelService.inserirOuAlterar(aluguel);
      }

}

package edu.ifma.locacaodeimoveis.builder;

import edu.ifma.locacaodeimoveis.model.Aluguel;
import edu.ifma.locacaodeimoveis.model.Locacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AluguelBuilder {
    private Aluguel aluguel;

    private AluguelBuilder() {
    }

    public static AluguelBuilder umAluguel() {
        Locacao locacao = LocacaoBuilder.umaLocacao().comId(3L).constroi();
        AluguelBuilder builder = new AluguelBuilder();
        builder.aluguel = new Aluguel();
        builder.aluguel.setDataDeVencimento(LocalDate.of(2020, 12, 10));
        builder.aluguel.setDataDePagamento(LocalDate.of(2020, 12, 4));
        builder.aluguel.setLocacao(locacao);
        builder.aluguel.setObservacao("Proximo à praia");
        builder.aluguel.setValorPago(BigDecimal.valueOf(500));
        return builder;
    }

    public AluguelBuilder comId(Long id) {
        aluguel.setIdAluguel(id);
        return this;
    }

    public AluguelBuilder paraUmCliente(String nome) {
        aluguel.getLocacao().getInquilino().setNomeCliente(nome);
        return this;
    }

    public AluguelBuilder comValorAluguel(BigDecimal valor) {
        aluguel.getLocacao().setValorAluguel(valor);
        return this;
    }

    public AluguelBuilder queAindaNaoFoiPago() {
        aluguel.setValorPago(BigDecimal.ZERO);
        return this;
    }

    public AluguelBuilder emAtraso() {
        aluguel.setDataDePagamento(aluguel.getDataDeVencimento().plusDays(1));
        return this;
    }

    public AluguelBuilder comDataDeVencimento(LocalDate dataVencimento) {
        aluguel.setDataDeVencimento(dataVencimento);
        return this;
    }

    public AluguelBuilder comDataDePagamento(LocalDate dataPagamento) {
        aluguel.setDataDePagamento(dataPagamento);
        return this;
    }

    public AluguelBuilder comPagamentoNoValorDe(BigDecimal valor) {
        aluguel.setValorPago(valor);
        return this;
    }

    public AluguelBuilder paraUmaLocacao(Locacao locacao) {
        aluguel.setLocacao(locacao);
        return this;
    }

    public Aluguel constroi() {
        return aluguel;
    }
}

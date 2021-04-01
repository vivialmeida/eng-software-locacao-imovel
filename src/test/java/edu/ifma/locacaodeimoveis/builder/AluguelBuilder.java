package edu.ifma.locacaodeimoveis.builder;

import edu.ifma.locacaodeimoveis.model.Aluguel;
import edu.ifma.locacaodeimoveis.model.LocacaoImovel;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AluguelBuilder {
    private Aluguel aluguel;

    private AluguelBuilder() {
    }

    public static AluguelBuilder umAluguel() {
        LocacaoImovel locacao = LocacaoBuilder.umaLocacao().comId(3).constroi();
        AluguelBuilder builder = new AluguelBuilder();
        builder.aluguel = new Aluguel();
        builder.aluguel.setDataVencimento(LocalDate.of(2020, 12, 10));
        builder.aluguel.setDataPagamento(LocalDate.of(2020, 12, 4));
        builder.aluguel.setLocacao(locacao);
        builder.aluguel.setValorPago(BigDecimal.valueOf(500));
        return builder;
    }

    public AluguelBuilder comId(Integer id) {
        aluguel.setId(id);
        return this;
    }

    public AluguelBuilder paraUmCliente(String nome) {
        aluguel.getLocacao().getCliente().setNome(nome);
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
        aluguel.setDataPagamento(aluguel.getDataVencimento().plusDays(1));
        return this;
    }

    public AluguelBuilder comDataDeVencimento(LocalDate dataVencimento) {
        aluguel.setDataVencimento(dataVencimento);
        return this;
    }

    public AluguelBuilder comDataDePagamento(LocalDate dataPagamento) {
        aluguel.setDataPagamento(dataPagamento);
        return this;
    }

    public AluguelBuilder comPagamentoNoValorDe(BigDecimal valor) {
        aluguel.setValorPago(valor);
        return this;
    }

    public AluguelBuilder paraUmaLocacao(LocacaoImovel locacao) {
        aluguel.setLocacao(locacao);
        return this;
    }

    public Aluguel constroi() {
        return aluguel;
    }
}

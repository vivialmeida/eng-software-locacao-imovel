package edu.ifma.locacaodeimoveis.builder;

import edu.ifma.locacaodeimoveis.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LocacaoBuilder {
    private LocacaoImovel locacao;

    private LocacaoBuilder() {
    }

    public static LocacaoBuilder umaLocacao() {
        LocacaoBuilder builder = new LocacaoBuilder();
        Cliente cliente = ClienteBuilder.umCliente().comId(1).constroi();
        Imovel imovel = ImovelBuilder.umImovel().constroi();
        builder.locacao = new LocacaoImovel();
        builder.locacao.setCliente(cliente);
        builder.locacao.setImovel(imovel);
        builder.locacao.setValorAluguel(BigDecimal.valueOf(500));
        builder.locacao.setDiaVencimento(10);
        builder.locacao
                .setDataInicio(LocalDate.of(2020, 12, 10));
        builder.locacao
                .setDataFim(LocalDate.of(2020, 12, 17));
        builder.locacao.setAtivo(true);
        builder.locacao.setPeriodicidade("mes");
        builder.locacao.setResponsavelPagamento("Inquilino");
        return builder;
    }

    public LocacaoBuilder comId(Integer id) {
        locacao.setId(id);
        return this;
    }

    public LocacaoBuilder ativo(boolean ativo) {
        locacao.setAtivo(ativo);
        return this;
    }

    public LocacaoBuilder comValorAluguelSugerido(BigDecimal valor) {
        locacao.getImovel().setValorAluguelSugerido(valor);
        return this;
    }

    public LocacaoBuilder tipo(TipoImovel tipo) {
        locacao.getImovel().setTipoImovel(tipo);
        return this;
    }

    public LocacaoBuilder comDataInicio(LocalDate data) {
        locacao.setDataInicio(data);
        return this;
    }

    public LocacaoBuilder noEndereco(String bairro) {
        EnderecoImovel enderecoImovel = new EnderecoImovel();
        enderecoImovel.setBairro("Cohama");
        enderecoImovel.setCep("65532-884");
        enderecoImovel.setZonaCidade(ZonaCidade.CENTRAL);
        locacao.getImovel().setEnderecoImovel(enderecoImovel);
        return this;
    }

    public LocacaoBuilder paraUmClienteDeNome(String nome) {
        locacao.getCliente().setNome(nome);
        return this;
    }

    public LocacaoBuilder paraUmCliente(Cliente cliente) {
        locacao.setCliente(cliente);
        return this;
    }

    public LocacaoBuilder paraUmImovel(Imovel imovel) {
        locacao.setImovel(imovel);
        return this;
    }

    public LocacaoImovel constroi() {
        return locacao;
    }
}

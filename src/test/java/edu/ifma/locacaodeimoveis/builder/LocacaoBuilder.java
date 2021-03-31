package edu.ifma.locacaodeimoveis.builder;

import edu.ifma.locacaodeimoveis.model.Cliente;
import edu.ifma.locacaodeimoveis.model.Imovel;
import edu.ifma.locacaodeimoveis.model.Locacao;
import edu.ifma.locacaodeimoveis.model.TipoImovel;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LocacaoBuilder {
    private Locacao locacao;

    private LocacaoBuilder() {
    }

    public static LocacaoBuilder umaLocacao() {
        LocacaoBuilder builder = new LocacaoBuilder();
        Cliente cliente = ClienteBuilder.umCliente().comId(1L).constroi();
        Imovel imovel = ImovelBuilder.umImovel().comId(2L).constroi();
        builder.locacao = new Locacao(imovel, cliente);
        builder.locacao.setValorAluguel(BigDecimal.valueOf(500));
        builder.locacao.setPercentualMulta(0);
        builder.locacao.setDiaVencimento(10);
        builder.locacao
                .setDataInicio(LocalDate.of(2020, 12, 10));
        builder.locacao
                .setDataFim(LocalDate.of(2020, 12, 17));
        builder.locacao.setAtivo(true);
        builder.locacao.setObservacao("Próximo a hospital e shoppping");
        return builder;
    }

    public LocacaoBuilder comId(Long id) {
        locacao.setIdLocacao(id);
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

    public LocacaoBuilder noBairro(String bairro) {
        locacao.getImovel().setBairro(bairro);
        return this;
    }

    public LocacaoBuilder paraUmClienteDeNome(String nome) {
        locacao.getInquilino().setNomeCliente(nome);
        return this;
    }

    public LocacaoBuilder paraUmCliente(Cliente cliente) {
        locacao.setInquilino(cliente);
        return this;
    }

    public LocacaoBuilder paraUmImovel(Imovel imovel) {
        locacao.setImovel(imovel);
        return this;
    }

    public Locacao constroi() {
        return locacao;
    }
}

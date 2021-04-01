package edu.ifma.locacaodeimoveis.builder;

import edu.ifma.locacaodeimoveis.model.EnderecoImovel;
import edu.ifma.locacaodeimoveis.model.ZonaCidade;

public class EnderecoBuilder {
    private EnderecoImovel enderecoImovel;

    private EnderecoBuilder() {
    }

    public static EnderecoBuilder umEndereco() {
        EnderecoBuilder builder = new EnderecoBuilder();
        builder.enderecoImovel = new EnderecoImovel();
        builder.enderecoImovel.setLogradouro("Casa da familia Marques");
        builder.enderecoImovel.setBairro("cohama");
        builder.enderecoImovel.setCep("65894325");
        builder.enderecoImovel.setPontoDeReferencia("px ao mateus");
        builder.enderecoImovel.setZonaCidade(ZonaCidade.OESTE);

        return builder;
    }

    public EnderecoImovel constroi() {
        return enderecoImovel;
    }

}

package edu.ifma.locacaodeimoveis.builder;

import edu.ifma.locacaodeimoveis.model.Imovel;
import edu.ifma.locacaodeimoveis.model.TipoImovel;

import java.math.BigDecimal;

public class ImovelBuilder {
    private Imovel imovel;

    private ImovelBuilder() {
    }

    public Imovel constroi() {
        return imovel;
    }

    public ImovelBuilder comId(Integer id) {
        imovel.setId(id);
        return this;
    }
    public ImovelBuilder comTipo(TipoImovel tipoImovel) {
        imovel.setTipoImovel(tipoImovel);
        return this;
    }
    public ImovelBuilder comValor(BigDecimal valor) {
        imovel.setValorAluguelSugerido(valor);
        return this;
    }

    public static ImovelBuilder umImovel() {
        ImovelBuilder builder = new ImovelBuilder();
        builder.imovel = new Imovel();
        builder.imovel.setValorAluguelSugerido(BigDecimal.valueOf(800));
        builder.imovel.setVagasGaragem(1);
        builder.imovel.setNomeImovel("cs aurora");
        builder.imovel.setQuantidadeBanheiros(2);
        builder.imovel.setQuantidadeSuites(1);
        builder.imovel.setQuantidadeDomitorios(2);
        builder.imovel.setMetragem(new BigDecimal(47.8));
        builder.imovel.setCliente(ClienteBuilder.umCliente().constroi());
        builder.imovel.setEnderecoImovel(EnderecoBuilder.umEndereco().constroi());
        return builder;
    }
}

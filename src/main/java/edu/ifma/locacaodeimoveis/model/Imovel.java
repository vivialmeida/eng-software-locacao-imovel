package edu.ifma.locacaodeimoveis.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
public class Imovel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idImovel;

    @Enumerated(EnumType.ORDINAL)
    private TipoImovel tipoImovel;
    @NotEmpty(message = "O nome do imóvel deve ser informado")
    private String nome;
    @NotEmpty(message = "O endereco deve ser informado")
    private String endereco;
    @NotEmpty(message = "O bairro do imóvel deve ser informado")
    private String bairro;
    private int cep;
    private double metragem;
    private int dormitorios;
    private int banheiros;
    private int suites;
    private int vagasGaragem;
    private BigDecimal valorAluguelSugerido;
    private String observacao;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
        result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Imovel)) {
            return false;
        }
        Imovel other = (Imovel) obj;
        if (nome == null) {
            if (other.nome != null) {
                return false;
            }
        } else if (!nome.equals(other.nome)) {
            return false;
        }
        if (endereco == null) {
            if (other.endereco != null) {
                return false;
            }
        } else if (!endereco.equals(other.endereco)) {
            return false;
        }
        if (bairro == null) {
            return other.bairro == null;
        } else return bairro.equals(other.bairro);
    }
}

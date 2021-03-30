package edu.ifma.locacaodeimoveis.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Locacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idLocacao;

    private boolean ativo;
    @NotEmpty(message = "O valor do aluguel deve ser preenchido")
    private BigDecimal valorAluguel;
    private double percentualMulta;
    private int diaVencimento;
    @NotEmpty(message = "A data de inicio deve ser informada")
    private LocalDate dataInicio;
    @NotEmpty(message = "A data final deve ser informada")
    private LocalDate dataFim;
    private String observacao;

    @ManyToOne
    private Imovel imovel;

    @ManyToOne
    private Cliente inquilino;

    @OneToMany(mappedBy = "locacao")
    private List<Aluguel> aluguels;

    public Locacao() {
    }

    public Locacao(Imovel imovel, Cliente cliente) {
        this.imovel = imovel;
        this.inquilino = cliente;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((valorAluguel == null) ? 0 : valorAluguel.hashCode());
        result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
        result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
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
        if (!(obj instanceof Locacao)) {
            return false;
        }
        Locacao other = (Locacao) obj;
        if (valorAluguel == null) {
            if (other.valorAluguel != null) {
                return false;
            }
        } else if (!valorAluguel.equals(other.valorAluguel)) {
            return false;
        }
        if (dataInicio == null) {
            if (other.dataInicio != null) {
                return false;
            }
        } else if (!dataInicio.equals(other.dataInicio)) {
            return false;
        }
        if (dataFim == null) {
            return other.dataFim == null;
        } else return dataFim.equals(other.dataFim);
    }

}

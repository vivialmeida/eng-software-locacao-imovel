package edu.ifma.locacaodeimoveis.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idCliente;

    @NotEmpty(message = "O nome do cliente deve ser informado")
    private String nomeCliente;
    @NotEmpty(message = "O cpf do cliente deve ser informado")
    private String cpf;
    @NotEmpty(message = "O numero do celular deve ser informado")
    private String celular;
    @NotEmpty(message = "O email deve ser informado")
    private String email;
    @NotEmpty(message = "A data de nascimento deve ser informada")
    private LocalDate dtNascimento;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nomeCliente == null) ? 0 : nomeCliente.hashCode());
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + ((celular == null) ? 0 : celular.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((dtNascimento == null) ? 0 : dtNascimento.hashCode());
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
        if (!(obj instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) obj;
        if (nomeCliente == null) {
            if (other.nomeCliente != null) {
                return false;
            }
        } else if (!nomeCliente.equals(other.nomeCliente)) {
            return false;
        }
        if (cpf == null) {
            if (other.cpf != null) {
                return false;
            }
        } else if (!cpf.equals(other.cpf)) {
            return false;
        }
        if (celular == null) {
            if (other.celular != null) {
                return false;
            }
        } else if (!celular.equals(other.celular)) {
            return false;
        }
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (dtNascimento == null) {
            return other.dtNascimento == null;
        } else return dtNascimento.equals(other.dtNascimento);
    }

}

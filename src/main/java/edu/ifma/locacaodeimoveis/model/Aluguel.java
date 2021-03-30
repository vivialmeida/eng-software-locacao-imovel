package edu.ifma.locacaodeimoveis.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@Entity
public class Aluguel implements Serializable {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "id")
      private Long idAluguel;

      @ManyToOne
      private Locacao locacao;
      @NotEmpty(message = "A data de vencimento deve ser preenchida")
      private LocalDate dataDeVencimento;
      @NotEmpty(message = "O valor pago deve ser preenchido")
      private BigDecimal valorPago;
      @NotEmpty(message = "A data de pagamento deve ser preenchida")
      private LocalDate dataDePagamento;
      private String observacao;

      @Override
      public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((dataDeVencimento == null) ? 0 : dataDeVencimento.hashCode());
            result = prime * result + ((valorPago == null) ? 0 : valorPago.hashCode());
            result = prime * result + ((dataDePagamento == null) ? 0 : dataDePagamento.hashCode());
            return result;
      }

      public BigDecimal valorASerPagoComMulta() {

            if (dataDePagamento.isAfter(dataDeVencimento)) {

                  long diferencaEmDias = ChronoUnit.DAYS.between(dataDeVencimento, dataDePagamento);
                  BigDecimal valorAluguel = locacao.getValorAluguel();
                  BigDecimal multa = null;
                  BigDecimal tetoMulta = valorAluguel.multiply(new BigDecimal(0.8)).setScale(2, RoundingMode.HALF_UP);


                  multa = new BigDecimal(String.valueOf(valorAluguel.multiply(new BigDecimal("0.0033"))));
                  multa = multa.multiply(new BigDecimal(Long.toString(diferencaEmDias)));

                  if (multa.compareTo(tetoMulta) < 1) {
                        valorAluguel = valorAluguel.add(multa);
                  } else {
                        valorAluguel.add(tetoMulta);
                  }
                  return valorAluguel;

            } else {

                  return locacao.getValorAluguel().setScale(2, RoundingMode.HALF_UP);

            }
      }

      @Override
      public boolean equals(Object obj) {
            if (this == obj) {
                  return true;
            }
            if (obj == null) {
                  return false;
            }
            if (!(obj instanceof Aluguel)) {
                  return false;
            }
            Aluguel other = (Aluguel) obj;
            if (dataDeVencimento == null) {
                  if (other.dataDeVencimento != null) {
                        return false;
                  }
            } else if (!dataDeVencimento.equals(other.dataDeVencimento)) {
                  return false;
            }
            if (valorPago == null) {
                  if (other.valorPago != null) {
                        return false;
                  }
            } else if (!valorPago.equals(other.valorPago)) {
                  return false;
            }
            if (dataDePagamento == null) {
                  return other.dataDePagamento == null;
            } else return dataDePagamento.equals(other.dataDePagamento);
      }

}

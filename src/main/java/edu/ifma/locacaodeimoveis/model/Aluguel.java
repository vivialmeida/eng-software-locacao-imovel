package edu.ifma.locacaodeimoveis.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import javax.persistence.*;

@Data
@Entity
@Table(name = "aluguel")
public class Aluguel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "data_vencimento", nullable = false)
	private LocalDate dataVencimento; 
	
	@Column(name = "data_pagamento", nullable = true)
	private LocalDate dataPagamento;
	
	@Column(name = "valor_pagamento", precision = 10, scale = 2, nullable = true)
	private BigDecimal valorPago;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_locacao")
	private LocacaoImovel locacao;

	public BigDecimal valorASerPagoComMulta() {
		
		if (dataPagamento.isAfter(dataVencimento)) {
			
			long diferencaEmDias 	= ChronoUnit.DAYS.between(dataVencimento, dataPagamento);
			BigDecimal valorAluguel = locacao.getValorAluguel();	
			BigDecimal juro 		= null;
			
			juro = valorAluguel.multiply(new BigDecimal("0.2")); 				
			juro = juro.multiply(new BigDecimal("0.0033")); 						
			juro = juro.multiply(new BigDecimal(Long.toString(diferencaEmDias))); 	
			
			return valorAluguel.add(juro);
		
		} else {
			
			return locacao.getValorAluguel();
			
		}
	}

}

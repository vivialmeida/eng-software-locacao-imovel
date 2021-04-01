package edu.ifma.locacaodeimoveis.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.LinkedHashSet;

import javax.persistence.*;

@Data
@Entity
@Table(name = "locacao_imovel")
public class LocacaoImovel {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "valor_aluguel", precision = 10, scale = 2, nullable = false)
	private BigDecimal valorAluguel;
	
	@Column(length = 100, nullable = false)
	private String periodicidade;
	
	@Column(name = "dia_vencimento", nullable = false)
	private Integer diaVencimento;
	
	@Column(name = "data_inicio", nullable = false)
	private LocalDate dataInicio;
	
	@Column(name = "data_fim", nullable = false)
	private LocalDate dataFim;
	
	@Column(name = "responsavel_pagamento", length = 100, nullable = false)
	private String responsavelPagamento;
	
	@Column(nullable = false)
	private boolean ativo;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "codigo_imovel")
	private Imovel imovel;

	@ManyToOne(optional = false)
	@JoinColumn(name = "codigo_inquilino")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "locacao")
	private Set<Aluguel> alugueis = new LinkedHashSet<Aluguel>();

	@PrePersist
	private void prePersist() {
		this.dataInicio = LocalDate.now();
	}


	
	
	
	

}

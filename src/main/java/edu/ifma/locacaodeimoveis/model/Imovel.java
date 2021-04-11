package edu.ifma.locacaodeimoveis.model;

import lombok.Data;

import java.math.BigDecimal;

import java.util.Objects;
import java.util.Set;
import java.util.LinkedHashSet;


import javax.persistence.*;

@Data
@Entity
@Table(name = "imovel")
public class Imovel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome_imovel", length = 100, nullable = false)
	private String nomeImovel;

	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal metragem;

	@Column(name = "quantidade_dormitorios", nullable = false)
	private Integer quantidadeDomitorios;

	@Column(name = "quantidade_banheiros", nullable = false)
	private Integer quantidadeBanheiros;

	@Column(name = "quantidade_suites", nullable = false)
	private Integer quantidadeSuites;

	@Column(name = "vagas_garagem", nullable = false)
	private Integer vagasGaragem;

	@Column(name = "valor_aluguel_sugerido", precision = 10, scale = 2, nullable = true)
	private BigDecimal valorAluguelSugerido;

	@Column(name = "valor_iptu", precision = 10, scale = 2, nullable = true)
	private BigDecimal valorIPTU;

	@Column(name = "tipo_imovel")
	@Enumerated(EnumType.ORDINAL)
	private TipoImovel tipoImovel;

	@Embedded
	private EnderecoImovel enderecoImovel;

	@ManyToOne
	@JoinColumn(name = "codigo_proprietario")
	private Cliente cliente;

	@OneToMany(mappedBy = "imovel", cascade = CascadeType.MERGE)
	private Set<LocacaoImovel> locacoes = new LinkedHashSet<LocacaoImovel>();

}
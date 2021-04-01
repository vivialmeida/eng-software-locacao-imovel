package edu.ifma.locacaodeimoveis.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;


import java.io.Serializable;

@Data
@Embeddable
public class EnderecoImovel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(length = 100, nullable = false)
	private String logradouro;
	
	@Column(length = 100, nullable = false)
	private String bairro;
	
	@Column(length = 20, nullable = false)
	private String cep;
	
	@Column(name = "ponto_de_referencia", length = 100, nullable = true)
	private String pontoDeReferencia;
	
	@Column(name = "zona_cidade", nullable = true)
	@Enumerated(EnumType.STRING)
	private ZonaCidade zonaCidade;


}

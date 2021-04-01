package edu.ifma.locacaodeimoveis.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.LinkedHashSet;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToMany;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100, nullable = false)
	private String nome;
	
	@Column(length = 16, nullable = false)
	private String cpf;
	
	@Column(length = 16, nullable = false)
	private String rg;
	
	@Column(length = 16, nullable = true)
	private String cep;
	
	@Column(length = 100, nullable = true)
	private String endereco;
	
	@Column(length = 20, nullable = false)
	private String telefone1;
	
	@Column(length = 20, nullable = true)
	private String telefone2;
	
	@Column(length = 70, nullable = false)
	private String email;
	
	@Column(name = "data_nascimento", nullable = true)
	private LocalDate dataNascimento;
	
	@OneToMany(mappedBy = "cliente")
	private Set<LocacaoImovel> locacoes = new LinkedHashSet<LocacaoImovel>();

}

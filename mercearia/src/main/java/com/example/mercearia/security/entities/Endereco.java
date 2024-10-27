package com.example.mercearia.security.entities;

import br.com.cafe.security.dto.EnderecoResponseDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "endereco_cd_id")
	private Integer id;

	@Column(name = "endereco_tx_cep")
	private String cep;

	@Column(name = "endereco_tx_logradouro")
	private String logradouro;

	@Column(name = "endereco_tx_bairro")
	private String bairro;

	@Column(name = "endereco_tx_localidade")
	private String localidade;

	@Column(name = "endereco_tx_uf")
	private String uf;

	public Endereco() {
	}

	public Endereco(String cep, String logradouro, String bairro, String localidade, String uf) {
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public @NotBlank String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(@NotBlank String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}


}

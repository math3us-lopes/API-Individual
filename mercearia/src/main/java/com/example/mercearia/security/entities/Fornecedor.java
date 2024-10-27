package com.example.mercearia.security.entities;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fornecedor_cd_id")
	private Integer Id;

	@Column(name = "fornecedor_tx_nome")
	@NotBlank
	@Size(max = 30)
	private String nome;

	@Column(name = "fornecedor_int_cnpj")
	private String cnpj;

	@NotBlank
	@Email
	@Column(name = "fornecedor_tx_email")
	private String email;

	@ManyToOne
	@JoinColumn(name = "endereco_cd_id")
	private Endereco endereco;

	public Fornecedor(String nome, String cnpj, String email, Endereco endereco) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.email = email;
		this.endereco = endereco;

	}

	public Fornecedor() {

	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public @NotBlank @Size(max = 30) String getNome() {
		return nome;
	}

	public void setNome(@NotBlank @Size(max = 30) String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public @NotBlank @Email String getEmail() {
		return email;
	}

	public void setEmail(@NotBlank @Email String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}

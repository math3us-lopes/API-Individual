package com.example.mercearia.security.dto;

import br.com.cafe.security.entities.Fornecedor;

public class FornecedorResponseDTO {
	private String nome;
	private String email;
	private String cnpj;
	private EnderecoResponseDTO endereco;

	public FornecedorResponseDTO(String nome, String email, String cnpj, EnderecoResponseDTO endereco) {
		this.nome = nome;
		this.email = email;
		this.cnpj = cnpj;
		this.endereco = endereco;
	}

	public FornecedorResponseDTO() {

	}

	public FornecedorResponseDTO(Fornecedor fornecedor) {
	
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public EnderecoResponseDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoResponseDTO endereco) {
		this.endereco = endereco;
	}
}



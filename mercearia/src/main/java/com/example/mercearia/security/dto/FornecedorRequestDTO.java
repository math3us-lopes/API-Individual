package com.example.mercearia.security.dto;

public class FornecedorRequestDTO {
	private String nome;
	private String email;
	private String cnpj;
	private EnderecoRequestDTO endereco;

	public FornecedorRequestDTO(String nome, String email, String cnpj, EnderecoRequestDTO endereco) {
		this.nome = nome;
		this.email = email;
		this.cnpj = cnpj;
		this.endereco = endereco;
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

	public EnderecoRequestDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoRequestDTO endereco) {
		this.endereco = endereco;
	}

}

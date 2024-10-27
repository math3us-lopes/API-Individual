package com.example.mercearia.security.dto;

import br.com.cafe.security.entities.ProdutoEstoque;

public class ProdutoEstoqueResponseDTO {
	String nome;
	Double preco;
	Integer quantidade;
	CategoriaResponseDTO categoria;

	public ProdutoEstoqueResponseDTO(String nome, Double preco, Integer quantidade, CategoriaResponseDTO categoria) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.categoria = categoria;
	}

	public ProdutoEstoqueResponseDTO() {}

	public ProdutoEstoqueResponseDTO( String nome, Double preco, Integer quantidade) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public CategoriaResponseDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaResponseDTO categoria) {
		this.categoria = categoria;
	}

	
}

package com.example.mercearia.security.dto;

import java.util.Set;

import br.com.cafe.security.entities.ProdutoEstoque;

public class ProdutoEstoqueRequestDTO {
	String nome;
	Double preco;
	Integer quantidade;
	CategoriaRequestDTO categoria;

	public ProdutoEstoqueRequestDTO(String nome, Double preco, Integer quantidade, CategoriaRequestDTO categoria) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.categoria = categoria;
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

	public CategoriaRequestDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaRequestDTO categoria) {
		this.categoria = categoria;
	}

	public Set<ProdutoEstoque> toEntity() {
		
		return null;
	}
}

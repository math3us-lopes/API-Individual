package com.example.mercearia.security.dto;

import java.time.LocalDateTime;

public class PedidoCompraEstoqueRequestDTO {
	private Integer idPedido;
	private Integer idProduto;
	private Integer idFornecedor;
	private Integer quantidade;
	private Boolean status;
	private LocalDateTime dataPedido;

	public PedidoCompraEstoqueRequestDTO(Integer idPedido, Integer idProduto, Integer idFornecedor, Integer quantidade, Boolean status, LocalDateTime dataPedido) {
		this.idProduto = idProduto;
		this.idFornecedor = idFornecedor;
		this.quantidade = quantidade;
		this.status = status;
		this.dataPedido = dataPedido;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}
}

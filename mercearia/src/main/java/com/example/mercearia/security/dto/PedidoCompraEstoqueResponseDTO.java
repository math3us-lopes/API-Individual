package com.example.mercearia.security.dto;


import java.time.LocalDateTime;

public class PedidoCompraEstoqueResponseDTO {
	private Integer id;
	private Integer idFornecedor;
	private Integer idProduto;
	private Boolean status;
	private  Integer quantidade;

	public PedidoCompraEstoqueResponseDTO(Integer id, Integer idFornecedor, Integer idProduto, Boolean status, Integer quantidade) {
		this.id = id;
		this.idFornecedor = idFornecedor;
		this.idProduto = idProduto;
		this.status = status;
		this.quantidade = quantidade;
	}

	public PedidoCompraEstoqueResponseDTO(Integer id, String pedido, LocalDateTime dataPedido, Double preco) {

	}

	public PedidoCompraEstoqueResponseDTO() {

	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}

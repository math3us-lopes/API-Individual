package com.example.mercearia.security.dto;

import java.time.LocalDateTime;

public class PedidoCompraClienteResponseDTO {
    private Integer id;
    private String pedido;
    private LocalDateTime dataPedido;
    private Double precoTotal;
    private ProdutoEstoqueResponseDTO produtoEstoque;
    private EnderecoResponseDTO endereco;


    public PedidoCompraClienteResponseDTO() {}

    public PedidoCompraClienteResponseDTO(Integer id, String pedido, LocalDateTime dataPedido, Double preco) {
        this.id = id;
        this.pedido = pedido;
        this.dataPedido = dataPedido;
        this.precoTotal = preco;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public ProdutoEstoqueResponseDTO getProdutoEstoque() {
        return produtoEstoque;
    }

    public void setProdutoEstoque(ProdutoEstoqueResponseDTO produtoEstoque) {
        this.produtoEstoque = produtoEstoque;
    }

    public EnderecoResponseDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoResponseDTO endereco) {
        this.endereco = endereco;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }

}

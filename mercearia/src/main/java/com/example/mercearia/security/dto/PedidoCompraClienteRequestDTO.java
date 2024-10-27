package com.example.mercearia.security.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PedidoCompraClienteRequestDTO {
    private String pedido;
    private Integer quantidade;
    private LocalDateTime dataPedido;
    private  EnderecoRequestDTO endereco;

    public PedidoCompraClienteRequestDTO(String pedido, Integer quantidade, LocalDateTime dataPedido, EnderecoRequestDTO endereco) {
        this.pedido = pedido;
        this.quantidade = quantidade;
        this.dataPedido = dataPedido;
        this.endereco = endereco;
    }

    public PedidoCompraClienteRequestDTO() {}

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public EnderecoRequestDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoRequestDTO endereco) {
        this.endereco = endereco;
    }
}

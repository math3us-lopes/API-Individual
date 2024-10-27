package com.example.mercearia.security.entities;

import jakarta.mail.Address;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) 
@Table(name = "pedido_compra_cliente")
public class PedidoCompraCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ped_cd_id")
    private Integer id;

    @Column(name = "ped_txt_nome")
    private String pedido;

    @Column(name = "ped_nm_preco")
    private Double preco;

    public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

    @Column(name = "ped_txt_dataPedido")
    private LocalDateTime dataPedido;

    @ManyToMany
    @JoinTable(
            name="pedido_compra_cliente_produto",
            joinColumns = @JoinColumn(name = "ped_cd_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_estoque_cd_id")
    )
    private Set<ProdutoEstoque> produtosEstoques = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name="pedido_compra_cliente_enderecos",
            joinColumns = @JoinColumn(name = "ped_cd_id"),
            inverseJoinColumns = @JoinColumn(name = "endereco_cd_id")
    )
    private Set<Endereco> enderecos = new HashSet<>();

    public PedidoCompraCliente() {}

    public PedidoCompraCliente(Integer id, String pedido, Double preco, LocalDateTime dataPedido, Set<ProdutoEstoque> produtosEstoques) {
        this.id = id;
        this.pedido = pedido;
        this.preco = preco;
        this.dataPedido = dataPedido;
        this.produtosEstoques = produtosEstoques;
        this.enderecos = enderecos;

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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Set<ProdutoEstoque> getProdutosEstoques() {
        return produtosEstoques;
    }

    public void setProdutosEstoques(Set<ProdutoEstoque> produtosEstoques) {
        this.produtosEstoques = produtosEstoques;
    }
    
    public Set<Endereco> getEnderecos() {
		return enderecos;
	}

    public void setEndereco(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public void setUser(User user) {

    }

}
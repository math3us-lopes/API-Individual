package com.example.mercearia.security.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido_compra_estoque")
public class PedidoCompraEstoque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pce_cd_id")
	private Integer id;

	@Column(name = "pce_bl_status_recebido", nullable = false)
	private Boolean statusRecebido;


	@Column(name = "pce_data_pedido", nullable = false)
	private LocalDateTime dataPedido;

	
	@ManyToOne
	@JoinColumn(name="fornecedor_cd_id")
	private Fornecedor fornecedor;
	
	@ManyToMany
	@JoinTable(
			name="pedido_compra_estoque_produto",
			joinColumns = @JoinColumn(name = "pce_cd_id"),
			inverseJoinColumns = @JoinColumn(name = "produto_estoque_cd_id")
	)
	private Set<ProdutoEstoque> produtosEstoque = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private Integer quantidade;


	public PedidoCompraEstoque() {

	}

	public PedidoCompraEstoque(Double precoTotal,  Boolean statusRecebido,  LocalDateTime dataPedido, Fornecedor fornecedor, Set<ProdutoEstoque> produtosEstoque) {
		this.statusRecebido = statusRecebido;
		this.dataPedido = dataPedido;
		this.fornecedor = fornecedor;
		this.produtosEstoque = produtosEstoque;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getStatusRecebido() {
		return statusRecebido;
	}

	public void setStatusRecebido(Boolean statusRecebido) {
		this.statusRecebido = statusRecebido;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Fornecedor getFornecedores() {
		return fornecedor;
	}

	public void setFornecedores(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Set<ProdutoEstoque> getProdutosEstoque() {
		return produtosEstoque;
	}

	public void setProdutosEstoque(Set<ProdutoEstoque> produtosEstoque) {
		this.produtosEstoque = produtosEstoque;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer  getQuantidade() {
		return quantidade;
	}
}

package com.example.mercearia.security.entities;

import br.com.cafe.security.dto.CategoriaResponseDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "produto_estoque")
public class ProdutoEstoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_estoque_cd_id")
    private Integer id;

    @Size(max = 80)
    @NotBlank
    @Column(name = "produto_estoque_tx_nome")
    private String nome;

    @Column(name = "produto_estoque_nm_preco")
    private Double preco;

    @Column(name = "produto_estoque_int_quantidade")
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "categoria_cd_id")
    private Categoria categoria;


    public ProdutoEstoque(Integer id, String nome, Double preco, Integer quantidade, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    public ProdutoEstoque() {}




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public  Double getPreco() {
        return preco;
    }

    public void setPreco( Double preco) {
        this.preco = preco;
    }

    public  Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


}

package com.example.mercearia.security.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "foto")
public class Foto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "foto_cd_id")
    private String tipo;

    @Column(name = "foto_tx_nome")
    private String nome;

    @Column(name = "foto_nm_dados")
    private byte[] dados;

    @Column(name = "foto_tx_url")
    private String url;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="user_id")
    private User user;

    public Foto(String tipo, String nome, byte[] dados, User user, String url) {
        this.tipo = tipo;
        this.nome = nome;
        this.dados = dados;
        this.url = url;
        this.user = user;
    }

    public Foto() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getDados() {
        return dados;
    }

    public void setDados(byte[] dados) {
        this.dados = dados;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

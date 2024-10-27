package com.example.mercearia.security.dto;

import br.com.cafe.security.entities.Endereco;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // Omitir campos nulos na serialização
public class EnderecoResponseDTO {
    Integer id;
    private String cep;
    private String logradouro;
    private String bairro;
    private String uf;
    private String localidade;

    public EnderecoResponseDTO() {
    }

    public EnderecoResponseDTO(String cep, String logradouro, String bairro, String uf, String estado, String localidade) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.uf = uf;
        this.localidade = localidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }


    public static Endereco toEndereco(EnderecoResponseDTO enderecoResponseDTO) {
        Endereco endereco = new Endereco();
        endereco.setCep(enderecoResponseDTO.getCep());
        endereco.setLogradouro(enderecoResponseDTO.getLogradouro());
        endereco.setBairro(enderecoResponseDTO.getBairro());
        endereco.setUf(enderecoResponseDTO.getUf());
        endereco.setLocalidade(enderecoResponseDTO.getLocalidade());
        return endereco;
    }

}
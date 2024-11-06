package com.meialuastore.meialuastore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProdutoRequestDTO {

    @NotBlank(message = "O nome do produto é obrigatório.")
    private String nome;

    @NotBlank(message = "A descrição do produto é obrigatória.")
    private String descricao;

    @NotNull(message = "O preço do produto é obrigatório.")
    @Positive(message = "O preço deve ser maior que zero.")
    private Double preco;

    @NotBlank(message = "A categoria do produto é obrigatória.")
    private String categoria;

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}

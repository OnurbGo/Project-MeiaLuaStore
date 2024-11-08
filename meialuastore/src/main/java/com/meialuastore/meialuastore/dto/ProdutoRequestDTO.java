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

    public @NotBlank(message = "O nome do produto é obrigatório.") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome do produto é obrigatório.") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "A descrição do produto é obrigatória.") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message = "A descrição do produto é obrigatória.") String descricao) {
        this.descricao = descricao;
    }

    public @NotNull(message = "O preço do produto é obrigatório.") @Positive(message = "O preço deve ser maior que zero.") Double getPreco() {
        return preco;
    }

    public void setPreco(@NotNull(message = "O preço do produto é obrigatório.") @Positive(message = "O preço deve ser maior que zero.") Double preco) {
        this.preco = preco;
    }

    public @NotBlank(message = "A categoria do produto é obrigatória.") String getCategoria() {
        return categoria;
    }

    public void setCategoria(@NotBlank(message = "A categoria do produto é obrigatória.") String categoria) {
        this.categoria = categoria;
    }
}

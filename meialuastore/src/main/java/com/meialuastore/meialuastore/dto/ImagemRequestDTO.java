package com.meialuastore.meialuastore.dto;

public class ImagemRequestDTO {
    private Integer id_imagem;
    private String url;
    private Integer id_produto;

    // Getters e Setters
    public Integer getId_imagem() {
        return id_imagem;
    }

    public void setId_imagem(Integer id_imagem) {
        this.id_imagem = id_imagem;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }
}

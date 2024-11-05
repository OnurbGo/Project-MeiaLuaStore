package com.meialuastore.meialuastore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "imagens")
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_imagem;

    @Column(name = "url", columnDefinition = "longtext", nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}

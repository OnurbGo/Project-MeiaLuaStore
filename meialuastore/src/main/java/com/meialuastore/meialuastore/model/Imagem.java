package com.meialuastore.meialuastore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "imagens")
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagens")
    private Integer id_imagens;

    @Column(name = "url", columnDefinition = "longtext", nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
    private Produto produto;

    public Integer getId_imagens() {
        return id_imagens;
    }

    public void setId_imagens(Integer id_imagens) {
        this.id_imagens = id_imagens;
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


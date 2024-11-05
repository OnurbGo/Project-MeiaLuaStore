package com.meialuastore.meialuastore.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "auditoria_preco_item")
public class AuditoriaPrecoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_auditoria_preco_item;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @Column(name = "preco_antigo", nullable = false)
    private Double precoAntigo;

    @Column(name = "preco_novo", nullable = false)
    private Double precoNovo;

    @Column(name = "data_alteracao", nullable = false)
    private Timestamp dataAlteracao;

    // Getters e Setters
    public Integer getId_auditoria_preco_item() {
        return id_auditoria_preco_item;
    }

    public void setId_auditoria_preco_item(Integer id_auditoria_preco_item) {
        this.id_auditoria_preco_item = id_auditoria_preco_item;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Double getPrecoAntigo() {
        return precoAntigo;
    }

    public void setPrecoAntigo(Double precoAntigo) {
        this.precoAntigo = precoAntigo;
    }

    public Double getPrecoNovo() {
        return precoNovo;
    }

    public void setPrecoNovo(Double precoNovo) {
        this.precoNovo = precoNovo;
    }

    public Timestamp getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Timestamp dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}

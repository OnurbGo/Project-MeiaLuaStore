package com.meialuastore.meialuastore.dto;

import java.sql.Timestamp;

public class AuditoriaPrecoItemRequestDTO {
    private Integer id_auditoria_preco_item;
    private Integer id_produto;
    private Double precoAntigo;
    private Double precoNovo;
    private Timestamp dataAlteracao;

    // Getters e Setters
    public Integer getId_auditoria_preco_item() {
        return id_auditoria_preco_item;
    }

    public void setId_auditoria_preco_item(Integer id_auditoria_preco_item) {
        this.id_auditoria_preco_item = id_auditoria_preco_item;
    }

    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
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

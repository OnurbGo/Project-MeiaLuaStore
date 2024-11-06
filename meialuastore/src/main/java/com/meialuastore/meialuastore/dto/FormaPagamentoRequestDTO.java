package com.meialuastore.meialuastore.dto;

public class FormaPagamentoRequestDTO {

    private int idFormaPagamento;
    private String metodoForma;

    // Getters and Setters
    public int getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(int idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public String getMetodoForma() {
        return metodoForma;
    }

    public void setMetodoForma(String metodoForma) {
        this.metodoForma = metodoForma;
    }
}

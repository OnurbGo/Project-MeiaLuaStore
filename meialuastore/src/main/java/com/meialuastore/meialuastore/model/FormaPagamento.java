package com.meialuastore.meialuastore.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFormaPagamento;

    private String metodoForma;

    @OneToMany(mappedBy = "formaPagamento")
    private List<Pagamento> pagamentos;

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

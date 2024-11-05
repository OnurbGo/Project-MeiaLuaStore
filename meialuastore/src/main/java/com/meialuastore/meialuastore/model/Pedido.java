package com.meialuastore.meialuastore.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pedido;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "data_pedido")
    private java.sql.Timestamp dataPedido;

    @Column(name = "status")
    private String status;

    // Getters e Setters
    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public java.sql.Timestamp getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(java.sql.Timestamp dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id_pedido, pedido.id_pedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_pedido);
    }
}

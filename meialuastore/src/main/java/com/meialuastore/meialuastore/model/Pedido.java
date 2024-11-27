package com.meialuastore.meialuastore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pedido")
@JsonIgnoreProperties(value = { "usuario.login", "usuario.senha", "usuario.nome", "usuario.cpf" }) // Ignora campos desnecessários
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pedido;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference /*Evitar que aconteça o StackOverflowError (serializações infinitas)*/
    private Usuario usuario;

    @Column(name = "data_pedido")
    private Timestamp dataPedido;

    @Column(name = "status")
    private String status;

    /*cascade: é usado para propagar operações realizadas em uma entidade
    principal para entidades relacionadas ele é configurado nas anotações de relacionamento,
    como @OneToMany, @OneToOne*/

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProdutoPedido> produtosPedido;

    @Column(name = "quantidade")
    private Integer quantidade;

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @JsonProperty("usuario")  /* Exibe apenas o id_usuario */
    /*@JsonProperty personaliza como os campos de uma classe Java são mapeados para o JSON.*/
    public Integer getUsuarioId() {
        return usuario != null ? usuario.getId_usuario() : null;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Timestamp getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Timestamp dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProdutoPedido> getProdutosPedido() {
        return produtosPedido;
    }

    public void setProdutosPedido(List<ProdutoPedido> produtosPedido) {
        this.produtosPedido = produtosPedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
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

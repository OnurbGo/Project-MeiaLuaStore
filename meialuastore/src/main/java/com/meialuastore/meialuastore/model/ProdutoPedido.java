package com.meialuastore.meialuastore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "produto_pedido")
public class ProdutoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_produto_pedido;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    private Integer quantidade;

    public Integer getId_produto_pedido() {
        return id_produto_pedido;
    }

    public void setId_produto_pedido(Integer id_produto_pedido) {
        this.id_produto_pedido = id_produto_pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}

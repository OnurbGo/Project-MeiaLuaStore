package com.meialuastore.meialuastore.dto;

public class ProdutoPedidoRequestDTO {

    private Integer id_produto_pedido;
    private Integer id_pedido;
    private Integer id_produto;
    private Integer quantidade;

    // Getters e Setters
    public Integer getId_produto_pedido() {
        return id_produto_pedido;
    }

    public void setId_produto_pedido(Integer id_produto_pedido) {
        this.id_produto_pedido = id_produto_pedido;
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}

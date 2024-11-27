package com.meialuastore.meialuastore.model;

import java.io.Serializable;
import java.util.Objects;

public class ProdutoPedidoId implements Serializable /*usar para o fluxo de dados*/ {

    private Integer pedido;
    private Integer produto;

    public Integer getPedido() {
        return pedido;
    }

    public void setPedido(Integer pedido) {
        this.pedido = pedido;
    }

    public Integer getProduto() {
        return produto;
    }

    public void setProduto(Integer produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoPedidoId that = (ProdutoPedidoId) o;
        return Objects.equals(pedido, that.pedido) && Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, produto);
    }
}

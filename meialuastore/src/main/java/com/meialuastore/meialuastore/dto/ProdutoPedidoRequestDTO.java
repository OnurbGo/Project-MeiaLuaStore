package com.meialuastore.meialuastore.dto;

import java.util.Objects;

public class ProdutoPedidoRequestDTO {


    private Integer id_pedido;
    private Integer id_produto;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoPedidoRequestDTO that = (ProdutoPedidoRequestDTO) o;
        return Objects.equals(id_pedido, that.id_pedido) &&
                Objects.equals(id_produto, that.id_produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_pedido, id_produto);
    }
}

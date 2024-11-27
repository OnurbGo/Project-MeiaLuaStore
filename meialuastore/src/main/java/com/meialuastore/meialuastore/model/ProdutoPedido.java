package com.meialuastore.meialuastore.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "produto_pedido")
@IdClass(ProdutoPedidoId.class) /*usada para definir chaves primárias compostas*/
public class ProdutoPedido {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido", insertable = false, updatable = false)
    private Pedido pedido;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto", insertable = false, updatable = false)
    private Produto produto;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoPedido that = (ProdutoPedido) o;
        return pedido.equals(that.pedido) && produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, produto);
    }
}

package com.meialuastore.meialuastore.repository;

import com.meialuastore.meialuastore.model.ProdutoPedido;
import com.meialuastore.meialuastore.model.ProdutoPedidoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, ProdutoPedidoId> {

    @Query("SELECT pp FROM ProdutoPedido pp WHERE pp.pedido.id_pedido = :idPedido AND pp.produto.id_produto = :idProduto")
    Optional<ProdutoPedido> findByPedidoIdAndProdutoId(@Param("idPedido") Integer idPedido, @Param("idProduto") Integer idProduto);
}

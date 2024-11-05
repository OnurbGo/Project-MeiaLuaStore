package com.meialuastore.meialuastore.repository;

import com.meialuastore.meialuastore.model.ProdutoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, Integer> {
    // Você pode adicionar métodos personalizados aqui, se necessário
}

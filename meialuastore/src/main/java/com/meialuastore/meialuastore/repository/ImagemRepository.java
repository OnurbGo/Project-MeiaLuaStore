package com.meialuastore.meialuastore.repository;

import com.meialuastore.meialuastore.model.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Integer> {
    @Query("SELECT i FROM Imagem i WHERE i.produto.id_produto = :id_produto")
    List<Imagem> findByProduto_Id_produto(@Param("id_produto") Integer id_produto);
}

package com.meialuastore.meialuastore.repository;

import com.meialuastore.meialuastore.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // métodos de pesquisa personalizados aqui
}

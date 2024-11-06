package com.meialuastore.meialuastore.repository;

import com.meialuastore.meialuastore.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Integer> {
}
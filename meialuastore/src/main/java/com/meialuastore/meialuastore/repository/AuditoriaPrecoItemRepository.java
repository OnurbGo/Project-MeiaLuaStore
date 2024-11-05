package com.meialuastore.meialuastore.repository;

import com.meialuastore.meialuastore.model.AuditoriaPrecoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaPrecoItemRepository extends JpaRepository<AuditoriaPrecoItem, Integer> {
}

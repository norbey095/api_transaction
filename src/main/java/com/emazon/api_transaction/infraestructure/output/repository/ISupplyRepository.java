package com.emazon.api_transaction.infraestructure.output.repository;

import com.emazon.api_transaction.infraestructure.output.entity.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISupplyRepository extends JpaRepository<SupplyEntity, Integer> {
}

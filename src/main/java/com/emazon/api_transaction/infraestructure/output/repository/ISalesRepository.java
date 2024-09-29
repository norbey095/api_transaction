package com.emazon.api_transaction.infraestructure.output.repository;

import com.emazon.api_transaction.infraestructure.output.entity.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ISalesRepository extends JpaRepository<SalesEntity, Integer> {

    @Query("SELECT a FROM SalesEntity a " +
            "WHERE (:email IS NULL OR a.email = :email)"+
            "AND (:buyDate IS NULL OR a.buyDate = :buyDate)")
    List<SalesEntity> findSalesByUserName(@Param("email") String email,@Param("buyDate") LocalDateTime buyDate);

    @Query("DELETE FROM SalesEntity a " +
            "WHERE (:email IS NULL OR a.email = :email)"+
            "AND (:buyDate IS NULL OR a.buyDate = :buyDate)")
    void deleteSalesByUserName(@Param("email") String email,@Param("buyDate") LocalDateTime buyDate);
}

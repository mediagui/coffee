package com.inatlas.domain.db.repository.jpa;

import com.inatlas.domain.db.entity.OrderDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<OrderDB,Integer> {
  Optional<OrderDB> findOrderDBByCompleteFalse();

  @Query(value = "SELECT * FROM orderdb WHERE complete = true ORDER BY id DESC LIMIT 1", nativeQuery = true)
  Optional<OrderDB> findLastOrderDBCompleted();
}

package com.inatlas.domain.db.repository.jpa;

import com.inatlas.domain.db.entity.OrderDB;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderDB,Integer> {
}

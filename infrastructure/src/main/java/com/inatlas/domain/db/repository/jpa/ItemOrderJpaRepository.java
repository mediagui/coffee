package com.inatlas.domain.db.repository.jpa;

import com.inatlas.domain.db.entity.OrderItemDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrderJpaRepository extends JpaRepository<OrderItemDB,Integer> {



}

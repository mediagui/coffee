package com.inatlas.domain.db.repository.jpa;

import com.inatlas.domain.db.entity.OrderItemDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOrderJpaRepository extends JpaRepository<OrderItemDB,Integer> {
  void updateById(Integer id, Integer amount);

}

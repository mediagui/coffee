package com.inatlas.domain.repository;

import com.inatlas.domain.entity.Order;

import java.util.Optional;

public interface OrderRepository {
  Order createNewOrder();
  Optional<Order> getOrderByID(Integer id);
  void setCompleted(Integer id);
  Optional<Order> addProductToCurrentOrder(Integer itemId, Integer amount);
  Optional<Order> getActualOrder();
  Optional<Order> getLastOrderCompleted();
  Optional<Order> payOrder();

}

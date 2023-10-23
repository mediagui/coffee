package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Order;

import java.util.Optional;

public interface OperationsOnOrderUseCase {
  Order createNewOrder();
  Optional<Order> getOrderByID(Integer id);
  Optional<Order> getActualOrder();
  void setCompleted(Integer id);
  Optional<Order> addProductToOrder(Integer productId, Integer amount);

  Optional<Order> payOrder();
}

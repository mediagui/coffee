package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Order;

import java.util.Optional;

public interface OperationsOnOrderUseCase {
  Order createNewOrder();
  Optional<Order> getOrderByID(Integer id);
  void setCompleted(Integer id);
  void addProductToOrder(Integer orderId, Integer productId, Integer amount);

  void updateItemOrderFromOrder(Integer orderId, Integer productId, Integer amount);
}

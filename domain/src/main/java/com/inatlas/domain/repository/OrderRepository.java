package com.inatlas.domain.repository;

import com.inatlas.domain.entity.Order;

import java.util.Optional;

public interface OrderRepository {
  Order createNewOrder();
  Optional<Order> getOrderByID(Integer id);
  void setCompleted(Integer id);
  void addProductToOrder(Integer orderId, Integer itemId, Integer amount);

  void updateItemOrderFromOrder(Integer orderId, Integer itemId, Integer amount);


}

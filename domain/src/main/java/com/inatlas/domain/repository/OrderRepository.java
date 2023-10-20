package com.inatlas.domain.repository;

import com.inatlas.domain.entity.Order;

import java.util.Optional;

public interface OrderRepository {
  void createNewOrder();
  Optional<Order> getOrderByID(Integer id);
  boolean setCompleted(Integer id);
  void addProductToOrder(Integer id, Integer amount);
  void removeProductFromOrder(Integer orderId,Integer productId);


}

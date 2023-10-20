package com.inatlas.infra.service;

import com.inatlas.infra.api.ApiCoffeeShopDelegate;
import com.inatlas.infra.dto.OrderDTO;
import org.springframework.http.ResponseEntity;

public class OrderService implements ApiCoffeeShopDelegate {
  @Override
  public ResponseEntity<OrderDTO> createNewOrder() {
    return ApiCoffeeShopDelegate.super.createNewOrder();
  }

  @Override
  public ResponseEntity<OrderDTO> getActualOrder() {
    return ApiCoffeeShopDelegate.super.getActualOrder();
  }

  @Override
  public ResponseEntity<OrderDTO> addProductToOrder(Integer id, Integer amount) {
    return ApiCoffeeShopDelegate.super.addProductToOrder(id, amount);
  }
}

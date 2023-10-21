package com.inatlas.infra.service;

import com.inatlas.domain.db.mapper.OrderDTOMapper;
import com.inatlas.domain.usecase.OperationsOnOrderUseCase;
import com.inatlas.infra.api.ApiCoffeeShopDelegate;
import com.inatlas.infra.dto.OrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements ApiCoffeeShopDelegate {

  private final OperationsOnOrderUseCase operationsOnOrderUseCase;
  private final OrderDTOMapper orderDTOMapper;
  public OrderService(OperationsOnOrderUseCase operationsOnOrderUseCase, OrderDTOMapper orderDTOMapper) {
    this.operationsOnOrderUseCase = operationsOnOrderUseCase;
    this.orderDTOMapper = orderDTOMapper;
  }

  @Override
  public ResponseEntity<OrderDTO> createNewOrder() {
    return ResponseEntity.ok(orderDTOMapper.toDTO(operationsOnOrderUseCase.createNewOrder()));
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

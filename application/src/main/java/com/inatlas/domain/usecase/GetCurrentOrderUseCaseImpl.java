package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Order;
import com.inatlas.domain.repository.OrderRepository;

public class GetCurrentOrderUseCaseImpl implements GetCurrentOrderUseCase {

  private final OrderRepository orderRepository;

  public GetCurrentOrderUseCaseImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public Order getCurrentOrder() {
    return orderRepository.getActualOrder().orElseGet(orderRepository::createNewOrder);
  }
}

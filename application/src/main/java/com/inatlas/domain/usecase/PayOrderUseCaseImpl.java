package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Order;
import com.inatlas.domain.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PayOrderUseCaseImpl implements PayOrderUseCase {

  private final OrderRepository orderRepository;

  public PayOrderUseCaseImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public Optional<Order> payOrder() {
    return orderRepository.payOrder();
  }
}

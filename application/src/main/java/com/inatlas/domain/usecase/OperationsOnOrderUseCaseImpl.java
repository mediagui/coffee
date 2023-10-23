package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Order;
import com.inatlas.domain.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OperationsOnOrderUseCaseImpl implements OperationsOnOrderUseCase {

  private final OrderRepository orderRepository;
  private final PayOrderUseCase payOrderUseCase;

  public OperationsOnOrderUseCaseImpl(OrderRepository orderRepository, PayOrderUseCase payOrderUseCase) {
    this.orderRepository = orderRepository;
    this.payOrderUseCase = payOrderUseCase;
  }

  @Override
  public Order createNewOrder() {
    return orderRepository.createNewOrder();
  }

  @Override
  public Optional<Order> getOrderByID(Integer id) {
    return orderRepository.getOrderByID(id);
  }

  @Override
  public void setCompleted(Integer id) {
    orderRepository.setCompleted(id);

  }

  @Override
  public Optional<Order> addProductToOrder(Integer productId, Integer amount) {
     return orderRepository.addProductToCurrentOrder(productId, amount);
  }


  @Override
  public Optional<Order> getActualOrder() {
    return orderRepository.getActualOrder();
  }

  @Override
  public Optional<Order> payOrder() {
    return payOrderUseCase.payOrder();
  }
}

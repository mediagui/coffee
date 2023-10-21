package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Order;
import com.inatlas.domain.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OperationsOnOrderUseCaseImpl implements OperationsOnOrderUseCase {

  private final OrderRepository orderRepository;

  public OperationsOnOrderUseCaseImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
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
  public void addProductToOrder(Integer orderId, Integer productId, Integer amount) {
    orderRepository.addProductToOrder(orderId, productId, amount);
  }

  @Override
  public void updateItemOrderFromOrder(Integer orderId, Integer productId, Integer amount) {
    orderRepository.updateItemOrderFromOrder(orderId, productId, amount);

  }


}

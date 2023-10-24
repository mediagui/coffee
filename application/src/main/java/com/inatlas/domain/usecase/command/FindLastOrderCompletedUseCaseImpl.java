package com.inatlas.domain.usecase.command;

import com.inatlas.domain.entity.Order;
import com.inatlas.domain.repository.OrderRepository;
import com.inatlas.domain.usecase.FindLastOrderCompletedUseCase;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindLastOrderCompletedUseCaseImpl implements FindLastOrderCompletedUseCase {

  private final OrderRepository orderRepository;

  public FindLastOrderCompletedUseCaseImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public Optional<Order> getLastOrderCompleted(){
    return orderRepository.getLastOrderCompleted();}
}

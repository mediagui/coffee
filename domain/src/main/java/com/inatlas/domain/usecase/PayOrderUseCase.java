package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Order;

import java.util.Optional;

public interface PayOrderUseCase {
  Optional<Order> payOrder();
}

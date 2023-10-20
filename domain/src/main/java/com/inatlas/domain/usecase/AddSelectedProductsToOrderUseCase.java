package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.OrderItem;

import java.util.List;

public interface AddSelectedProductsToOrderUseCase {
  void addProduct(int choice, List<OrderItem> orderItems);
}

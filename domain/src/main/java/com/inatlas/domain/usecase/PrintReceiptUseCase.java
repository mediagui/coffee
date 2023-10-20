package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Order;

public interface PrintReceiptUseCase {

  void print(Order order);
}

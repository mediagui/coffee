package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.OrderItem;

import java.util.List;

public interface PromotionUseCase {

  boolean applyPromotion(List<OrderItem> orderItems);
}

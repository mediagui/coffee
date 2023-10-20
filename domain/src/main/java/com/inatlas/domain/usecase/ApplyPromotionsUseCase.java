package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Order;
import com.inatlas.domain.entity.Promotion;

import java.util.Optional;

public interface ApplyPromotionsUseCase {

  Optional<Promotion> applyAndGetTheBestPromotion(Order order);

}

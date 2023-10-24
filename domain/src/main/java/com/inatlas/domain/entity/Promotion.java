package com.inatlas.domain.entity;

import com.inatlas.domain.usecase.PromotionUseCase;

import java.util.List;

public class Promotion {
  public double getAmount() {
    return amount;
  }

  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  private final double amount;
  private final List<OrderItem> orderItems;
  private final PromotionUseCase promotionUseCase;

  public Promotion(double costAmount, List<OrderItem> orderItems, PromotionUseCase promotionUseCase) {
    this.amount = costAmount;
    this.orderItems = orderItems;
    //TODO: Refactor this to exclude the use case from the entity
    this.promotionUseCase = promotionUseCase;
  }
  public void applyTo(Order order){
    order.setPromotion(this.getName());
    promotionUseCase.applyPromotion(order.getItems());
  }

  public String getName() {

    int separatorPos = promotionUseCase.getClass().getSimpleName().indexOf("$");
    int toTheEnd = promotionUseCase.getClass().getSimpleName().length();


    return promotionUseCase.getClass().getSimpleName()
            .substring(0, separatorPos > 0 ? separatorPos : toTheEnd)
            .replace("Apply", "")
            .replace("UseCaseImpl", "")
            .replace("Use Case", "")
            .replaceAll("([A-Z])", " $1").trim();

  }
}

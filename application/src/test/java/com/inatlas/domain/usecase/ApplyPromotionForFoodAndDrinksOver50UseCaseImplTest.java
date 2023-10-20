package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.OrderItem;
import com.inatlas.domain.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Using @SpringJUnitConfig annotation, the Spring context is loaded for the test
// and then we can use @Autowired to inject the dependencies.
@SpringJUnitConfig(classes = {ApplyPromotionForFoodAndDrinksOver50UseCaseImpl.class})
class ApplyPromotionForFoodAndDrinksOver50UseCaseImplTest {


  @Autowired
  private ApplyPromotionForFoodAndDrinksOver50UseCase applyPromotionForFoodAndDrinksOver50UseCase;

  @Test
  @DisplayName("Test applied promotion with a valid amount of order items type and total amount over 50")
  void testApplyPromotionWithValidAmountOfProducts() {
    List<OrderItem> orderItems = new ArrayList<>();
    orderItems.add(new OrderItem(4, new Product(1, "Sandwich", 10.10, false, "Food")));
    orderItems.add(new OrderItem(2, new Product(2, "Cake Slice", 9.0, false, "Food")));
    orderItems.add(new OrderItem(1, new Product(3, "Latte", 5.3, false, "Drink")));

    boolean promotionApplied = applyPromotionForFoodAndDrinksOver50UseCase.applyPromotion(orderItems);

    assertTrue(promotionApplied);
  }

  @Test
  @DisplayName("Test promotion applied with no valid order items and amount over 50")
  void testApplyPromotionWithInValidOrderAmount() {
    List<OrderItem> orderItems = new ArrayList<>();
    orderItems.add(new OrderItem(4, new Product(1, "Sandwich", 10.10, false, "Food")));
    orderItems.add(new OrderItem(3, new Product(2, "Cake Slice", 9.0, false, "Food")));


    boolean promotionApplied = applyPromotionForFoodAndDrinksOver50UseCase.applyPromotion(orderItems);

    assertFalse(promotionApplied);
  }

  @Test
  @DisplayName("Test applied promotion with a valid amount of order items type and total amount not over 50")
  void testApplyPromotionWithValidAmountOfProductsButBelowLimitOf50() {
    List<OrderItem> orderItems = new ArrayList<>();
    orderItems.add(new OrderItem(1, new Product(1, "Sandwich", 10.10, false, "Food")));
    orderItems.add(new OrderItem(2, new Product(2, "Cake Slice", 9.0, false, "Food")));
    orderItems.add(new OrderItem(1, new Product(3, "Latte", 5.3, false, "Drink")));

    boolean promotionApplied = applyPromotionForFoodAndDrinksOver50UseCase.applyPromotion(orderItems);

    assertFalse(promotionApplied);
  }

}

// Tests End

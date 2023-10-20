package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Order;
import com.inatlas.domain.entity.OrderItem;
import com.inatlas.domain.entity.Product;
import com.inatlas.domain.entity.Promotion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;



class ApplyPromotionsUseCaseImplTest {
  @Mock
  ApplyPromotionForLatteUseCase applyPromotionForLatteUseCase;
  @Mock
  ApplyPromotionForTotalProductsUseCase applyPromotionForTotalProductsUseCase;
  @Mock
  ApplyPromotionForFoodAndDrinksOver50UseCase applyPromotionForFoodAndDrinksOver50UseCase;
  @InjectMocks
  ApplyPromotionsUseCaseImpl applyPromotionsUseCase;

  AutoCloseable closeable;

  @BeforeEach
  void setUp() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }





  @Test
  @DisplayName("Apply promotions to get the best promotion")
  void testApplyAndGetTheBestPromotion_Positive() {
    // Create order with some items

    List<OrderItem> orderItems = List.of(
      new OrderItem(5, new Product(1, "Latte", 5.3d,false, "Drink")),
      new OrderItem(4, new Product(2, "Sandwich", 10.10d,false, "Food")),
      new OrderItem(2, new Product(3, "Cake Slice", 9d,false, "Food")),
      new OrderItem(4, new Product(4, "Espresso", 4d,false, "Drink")));


    when(applyPromotionForLatteUseCase.applyPromotion(orderItems)).thenReturn(true);
    when(applyPromotionForTotalProductsUseCase.applyPromotion(orderItems)).thenReturn(true);
    when(applyPromotionForFoodAndDrinksOver50UseCase.applyPromotion(orderItems)).thenReturn(true);


    Order order = new Order(orderItems);
    Optional<Promotion> bestPromotion = applyPromotionsUseCase.applyAndGetTheBestPromotion(order);

    verify(applyPromotionForLatteUseCase, times(1)).applyPromotion(orderItems);
    verify(applyPromotionForTotalProductsUseCase, times(1)).applyPromotion(orderItems);
    verify(applyPromotionForFoodAndDrinksOver50UseCase, times(1)).applyPromotion(orderItems);

    assertNotEquals(Optional.empty(), bestPromotion);
    assertNotNull(bestPromotion.get());






//
//    // Apply promotions
//    Optional<Promotion> bestPromotion = applyPromotionsUseCase.applyAndGetTheBestPromotion(order);
//
//    // Assert the best promotion is applied correctly
//    assertTrue(bestPromotion.isPresent());
//    assertEquals("Latte Promotion", bestPromotion.get().getName());
//    assertEquals(2.5, bestPromotion.get().getAmount());
  }
}
// Tests End

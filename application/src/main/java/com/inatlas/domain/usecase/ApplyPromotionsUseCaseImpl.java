/**
 * This code implements the ApplyPromotionsUseCase interface and provides the implementation for applying promotions to an order.
 * It includes methods to choose the most beneficial promotion for the customer, check and apply promotions to the order items,
 * clone order items, and calculate the total order amount.
 */
package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Order;
import com.inatlas.domain.entity.OrderItem;
import com.inatlas.domain.entity.Promotion;
import com.inatlas.domain.repository.ProductRepository;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.inatlas.util.LogUtil.debug;
import static com.inatlas.util.PromotionUtil.checkPromotion;
import static com.inatlas.util.PromotionUtil.getMostBeneficialPromotion;

@Component
public class ApplyPromotionsUseCaseImpl implements ApplyPromotionsUseCase {
  private final ApplyPromotionForLatteUseCase applyPromotionForLatteUseCase;
  private final ApplyPromotionForTotalProductsUseCase applyPromotionForTotalProductsUseCase;
  private final ApplyPromotionForFoodAndDrinksOver50UseCase applyPromotionForFoodAndDrinksOver50UseCase;

  public ApplyPromotionsUseCaseImpl(ApplyPromotionForLatteUseCase applyPromotionForLatteUseCase, ApplyPromotionForTotalProductsUseCase applyPromotionForTotalProductsUseCase, ApplyPromotionForFoodAndDrinksOver50UseCase applyPromotionForFoodAndDrinksOver50UseCase) {
    this.applyPromotionForLatteUseCase = applyPromotionForLatteUseCase;
    this.applyPromotionForTotalProductsUseCase = applyPromotionForTotalProductsUseCase;
    this.applyPromotionForFoodAndDrinksOver50UseCase = applyPromotionForFoodAndDrinksOver50UseCase;
  }


  /**
   * Chooses the most beneficial promotion for the customer by checking and comparing the results of different promotions.
   *
   * @param order The order to applyTo promotions to.
   * @return The promotion result with the lowest amount.
   */
  @Override
  public Optional<Promotion> applyAndGetTheBestPromotion(Order order) {
    List<OrderItem> orderItems = order.getItems();

    debug("Checking promotions");

    Optional<Promotion> lattePromotion = checkPromotion(applyPromotionForLatteUseCase, orderItems);
    Optional<Promotion> totalProductsPromotion = checkPromotion(applyPromotionForTotalProductsUseCase, orderItems);
    Optional<Promotion> foodAndDrinksOver50Promotion = checkPromotion(applyPromotionForFoodAndDrinksOver50UseCase, orderItems);


    return getMostBeneficialPromotion(lattePromotion, totalProductsPromotion, foodAndDrinksOver50Promotion);

  }

//  private static int getAmountOfLattes(List<OrderItem> orderItems) {
//    return orderItems.stream().filter(item -> item.getProduct().getName().contains("Latte")).findFirst()
//            .orElseGet(() -> new OrderItem(0, null))
//            .getAmount();
//  }

}

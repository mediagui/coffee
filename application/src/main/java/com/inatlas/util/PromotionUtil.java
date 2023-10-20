package com.inatlas.util;

import com.inatlas.domain.entity.OrderItem;
import com.inatlas.domain.entity.Promotion;
import com.inatlas.domain.usecase.PromotionUseCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.inatlas.util.LogUtil.debug;

public class PromotionUtil {



  /**
   * Gets the most beneficial promotion from a list of promotions.
   * @param promotions The promotions to choose from.
   * @return The most beneficial promotion.
   */

  public static Optional<Promotion> getMostBeneficialPromotion(Optional<Promotion>... promotions) {

    debug("Choosing the most beneficial promotion");

    List<Optional<Promotion>> promotionsList =
            Arrays.asList(promotions)
                    .stream()
                    .filter(promotion -> promotion.isPresent())
                    .collect(Collectors.toList());

    if(!promotionsList.isEmpty()) {
      // Create a reference to the most beneficial promotion to avoid using a final
      // variable inside the lambda function. Using a final variable inside the lambda would
      // prevent us from modifying the variable inside it.
      var auxRef = new Object() {
        Optional<Promotion> mostBeneficialPromotion = promotionsList.get(0);
      };
      promotionsList.forEach(promotion -> {
        if (promotion.get().getAmount() < auxRef.mostBeneficialPromotion.get().getAmount()) {
          auxRef.mostBeneficialPromotion = promotion;
        }
      });

      return auxRef.mostBeneficialPromotion;
    }
    return Optional.empty();
  }

  /**
   * Checks and applies a promotion to the order items.
   *
   * @param promotionUseCase The promotion use case to applyTo.
   * @param items            The order items to applyTo the promotion to.
   * @return The promotion result after applying the promotion.
   */

  public static Optional<Promotion> checkPromotion(PromotionUseCase promotionUseCase, List<OrderItem> items) {

    debug("Checking promotion " + promotionUseCase.getClass().getSimpleName());

    List<OrderItem> clonedOrderItems = cloneOrderItems(items);
    boolean wasApplied = promotionUseCase.applyPromotion(clonedOrderItems);

    // If the promotion was applied, calculate the total amount of the order after applying the promotion.
    if (wasApplied) {
      double totalOrderAmountAfterPromotion = getTotalOrderAmount(clonedOrderItems);

      debug("Returning order amount after promotion applied: " + totalOrderAmountAfterPromotion);
      return Optional.of(new Promotion(totalOrderAmountAfterPromotion, clonedOrderItems, promotionUseCase));
    }
    // else return an empty optional
    return Optional.empty();
  }

  /**
   * Clones the order items by creating new instances of the order items.
   * @param orderItems The order items to clone.
   * @return The cloned order items.
   */
  public static List<OrderItem> cloneOrderItems(List<OrderItem> orderItems) {

    // Get free espressos in the list of orderItems
     List<OrderItem> freeEspressos = orderItems.stream().filter(item -> item.isPromotion()).collect(Collectors.toList());


    List<OrderItem> clonedOrderItems = new ArrayList<>();
    orderItems.stream()
            .filter(item -> !item.isPromotion())
            .forEach(orderItem -> {
      OrderItem clonedOrderItem = new OrderItem(orderItem);
      clonedOrderItems.add(clonedOrderItem);
    });

    clonedOrderItems.addAll(freeEspressos);

    debug("Order items cloned");


    return clonedOrderItems;
  }

  /**
   * Calculates the total amount of the order by summing the prices of the order items.
   * @param orderItems The order items to calculate the total amount from.
   * @return The total amount of the order.
   */
  public static double getTotalOrderAmount(List<OrderItem> orderItems) {
    debug("Calculating total order amount");
    return orderItems.stream().mapToDouble(orderItem -> orderItem.getProduct().getPrice() * orderItem.getAmount()).sum();
  }
}

package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.OrderItem;
import com.inatlas.util.LogUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.inatlas.util.LogUtil.*;

/**
 * This class implements the ApplyPromotionForFoodAndDrinksOver50UseCase interface.
 * It is responsible for applying a promotion to a list of order items.
 * It checks if the order items meet certain conditions, including having both food and drinks,
 * and the total order amount exceeding a specified value.
 * If the conditions are met, the promotion price for lattes in the order items is set.
 * The values for the conditions and the promotion price are read from the application.properties file.
 * The default values are:
 * - amountToApplyPromotion: 50
 * - mustHaveFoodAndDrink: true
 * - lattePromotionPrice: 3.0
 * The values can be overridden by setting the corresponding properties in the application.properties file.
 */
@Component
public class ApplyPromotionForFoodAndDrinksOver50UseCaseImpl implements ApplyPromotionForFoodAndDrinksOver50UseCase {

  @Value("${coffeeShop.over50.amountToApplyPromotion:50}")
  private double amountToApplyPromotion;

  @Value("${coffeeShop.over50.mustHaveFoodAndDrink:true}")
  private boolean mustHaveFoodAndDrink;

  @Value("${coffeeShop.over50.lattePrice:3.0}")
  private double lattePromotionPrice;



  public ApplyPromotionForFoodAndDrinksOver50UseCaseImpl() {

  }

  /**
 * This code applies a promotion to a list of order items.
 */
@Override
public boolean applyPromotion(List<OrderItem> orderItems) {
  boolean applied = false;

  debug("Checking if the order meets the conditions to apply the promotion");

  boolean meetFoodAndDrinkCondition = mustHaveFoodAndDrink && hasFoods(orderItems) && hasDrinks(orderItems);
  boolean meetAmountValueCondition = getTotalOrderAmount(orderItems) > amountToApplyPromotion ;

  if (meetFoodAndDrinkCondition && meetAmountValueCondition) {
    setLattePromotionPrice(orderItems, lattePromotionPrice);
    info("Promotion for Latte price reduced calculated");

    applied=true;

  }else{
    info("The order does not meet the conditions to apply the promotion");
  }
  return applied;
}

  private void setLattePromotionPrice(List<OrderItem> items, double price){
    items.stream().filter(item -> item.getProduct().getName().equalsIgnoreCase("latte")).forEach(item -> item.setUnitPrice(price));
    debug("Latte promotion price set to " + price + " for " + items.size() + " items");
  }

  private double getTotalOrderAmount(List<OrderItem> orderItems) {
    double totalOrderAmount = orderItems.stream().mapToDouble(orderItem -> orderItem.getProduct().getPrice() * orderItem.getAmount()).sum();
    debug("Total order amount: " + totalOrderAmount);
    return totalOrderAmount;
  }
  private boolean hasFoods(List<OrderItem> orderItems) {
    boolean hasFoods = orderItems.stream().anyMatch(orderItem -> orderItem.getProduct().getType().equalsIgnoreCase("FOOD"));
    debug("Has foods: " + hasFoods);
    return hasFoods;
  }

  private boolean hasDrinks(List<OrderItem> orderItems) {
    boolean hasDrinks = orderItems.stream().anyMatch(orderItem -> orderItem.getProduct().getType().equalsIgnoreCase("DRINK"));
    debug("Has drinks: " + hasDrinks);
    return hasDrinks;
  }

}

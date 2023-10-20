package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.OrderItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.inatlas.util.LogUtil.debug;
import static com.inatlas.util.LogUtil.info;

@Component
public class ApplyPromotionForTotalProductsUseCaseImpl implements ApplyPromotionForTotalProductsUseCase {

  @Value("${coffeeShop.discount5.totalProducts:8}")
  private int totalProductsForPromotion;

  @Value("${coffeeShop.discount5.discountPercentage:0.05}")
  private double discountForTotalProducts;


  /**
   * Applies a promotion to the given list of order items.
   * If the total number of products in the order exceeds a certain threshold,
   * a discount is applied to the total value of the order by reducing the unit price of each item.
   * The discount is reflected in the receipt.
   *
   * @param orderItems The list of order items to apply the promotion to.
   */
  @Override
  public boolean applyPromotion(List<OrderItem> orderItems) {
    boolean applied = false;

    // Calculate the amount of products in the order. It could be more than 1 unit of the same product
    // in each order item. Furthermore, the order could contain promotions and we don't want to count them.
    debug("Calculating total products in the order");
    int totalProducts = orderItems.stream()
            .filter(item -> !item.isPromotion())
            .mapToInt(OrderItem::getAmount).sum();

    // If the order contains more than 8 products, apply a 5% discount to the total value of the order.
    // Applying the discount to each of the items to reflect the discount in the receipt.
    debug("Checking if the order contains more than " + totalProductsForPromotion + " products");


    if (totalProducts > totalProductsForPromotion) {
      debug("Applying a " + discountForTotalProducts + " discount to the total value of the order");
      for (OrderItem item : orderItems) {
        if (item.getUnitPrice() > 0) {
          double price = BigDecimal.valueOf(item.getUnitPrice() / (1d + discountForTotalProducts)).setScale(2, RoundingMode.HALF_UP).doubleValue();
          item.setUnitPrice(price);
        }
      }
      info("Promotion for total amount of products calculated");
      applied=true;
    }else{
      debug("The order does not contain more than " + totalProductsForPromotion + " products");

    }

    return  applied;


  }
}

package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.OrderItem;
import com.inatlas.domain.entity.Product;
import com.inatlas.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.inatlas.util.LogUtil.debug;
import static com.inatlas.util.LogUtil.info;

@Component
public class ApplyPromotionForLatteUseCaseImpl implements ApplyPromotionForLatteUseCase {

  @Value("${coffeeShop.freeEspresso.numberOfLattes:2}")
  private int numberOfLattesForPromotion;

  ProductRepository productRepository;

  public ApplyPromotionForLatteUseCaseImpl(ProductRepository productRepository) {

    this.productRepository = productRepository;
  }

  /**
   * Apply promotion to the given list of order items.
   * If the number of lattes in the order is equal to or greater than the specified threshold,
   * the promotion "If you order 2 lattes, you receive a free espresso" is applied.
   * The number of free espressos to be given is calculated based on the number of lattes.
   * The order items are updated accordingly.
   *
   * @param orderItems The list of order items to apply the promotion to.
   */


  public boolean applyPromotion(List<OrderItem> orderItems) {

    boolean applied = false;

    int numberOfLattes = getNumberOfLattes(orderItems);

    if (numberOfLattes >= numberOfLattesForPromotion) {
      int freeEspressos = numberOfLattes / 2;
      if (freeEspressos > 0)
        updateEspressosOrderItem(orderItems, freeEspressos);

      applied = true;
      info("Promotion for free espressos calculated");
    }

    return applied;
  }

  private int getNumberOfLattes(List<OrderItem> orderItems) {

    int numberOfLattes = orderItems.stream().filter(item -> item.getProduct().getName().contains("Latte")).findFirst()
            .orElseGet(() -> new OrderItem(0, new Product(0, null, 0d, false, null)))
            .getAmount();

    debug("Amount of lattes in the order: " + numberOfLattes);

    return numberOfLattes;
  }


  /**
   * Update the order item for free espressos and add it to the order if it does not exist.
   * Also update the total amount of non-promotional espressos in the order.
   */
  private void updateEspressosOrderItem(List<OrderItem> orderItems, int freeEspressos) {
    OrderItem freeEspressosItem = getEspressosItem(orderItems, true);

    int freeEspressosAmountInOrderItem = freeEspressosItem.getAmount();

    int espressosAmountToAdd = Math.min(freeEspressos, freeEspressos - freeEspressosAmountInOrderItem);

    freeEspressosItem.setAmount(freeEspressosAmountInOrderItem + espressosAmountToAdd);

    if (freeEspressosItem.isNew()) {
      orderItems.add(freeEspressosItem);
    }

    debug("Updating the amount of free espressos in the order: " + freeEspressosItem.getAmount());

    updateNoPromotionalEspressosAmount(orderItems);

    info("Amount of espressos updated");
  }

  /**
   * Updates the amount of non-promotional espressos in the order.
   * If there are more paid espressos than free ones, the amount of paid espressos is updated.
   * Otherwise, the paid espressos item is removed from the order.
   *
   * @param orderItems the list of order items
   */
  private void updateNoPromotionalEspressosAmount(List<OrderItem> orderItems) {
    OrderItem paidEspressosItem = getEspressosItem(orderItems, false);
    OrderItem freeEspressosItem = getEspressosItem(orderItems, true);

    if (paidEspressosItem.getAmount() > freeEspressosItem.getAmount()) {
      int pos = orderItems.indexOf(paidEspressosItem);
      paidEspressosItem.setAmount(paidEspressosItem.getAmount() - freeEspressosItem.getAmount());
      orderItems.set(pos, paidEspressosItem);
    } else {
      orderItems.remove(paidEspressosItem);
    }

    debug("Updating the amount of paid espressos in the order: " + paidEspressosItem.getAmount());
  }

  /**
   * This code retrieves the "Espresso" item from a list of order items. If the item does not exist,
   * it creates a new one. The method takes in a list of order items and a boolean flag indicating
   * whether the item is a promotion. The method filters the list based on the item name and promotion
   * status to find the matching item. If no matching item is found, a new order item is created. The
   * method then returns a new instance of the found or created order item.
   */
  private OrderItem getEspressosItem(List<OrderItem> orderItems, boolean isPromotion) {
    String espressoName = "Espresso" + (isPromotion ? " Gratis *" : "");
    OrderItem orderItem = orderItems.stream().filter(item -> item.getProduct().getName().endsWith(espressoName) && item.getProduct().isPromotion() == isPromotion)
            .findFirst()
            .orElseGet(() -> {
              final boolean isPromotional = isPromotion;
              return createOrderItem(isPromotion, espressoName);
            });

    return new OrderItem(orderItem);
  }

  private OrderItem createOrderItem(boolean isPromotion, String espressoName) {

    Product product = productRepository.findByName("Espresso").get();
    product.setName(espressoName);
    product.setPromotion(isPromotion);

    debug("Order item created for product: " + product);

    return new OrderItem(0, product, isPromotion, true);
  }

}

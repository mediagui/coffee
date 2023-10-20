package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.OrderItem;
import com.inatlas.domain.entity.Product;
import com.inatlas.domain.repository.ProductRepository;
import com.inatlas.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.inatlas.util.LogUtil.debug;

/**
 * This class implements the AddSelectedProductsToOrderUseCase interface.
 * It is responsible for adding a product to a list of order items.
 * It retrieves the product from the product repository and updates its amount in the list.
 */
@Component
@Slf4j
public class AddSelectedProductsToOrderUseCaseImpl implements AddSelectedProductsToOrderUseCase{

  private final ProductRepository productRepository;

  public AddSelectedProductsToOrderUseCaseImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;

  }

  /**
   * Adds a product to the orderItems list based on the provided choice.
   * Retrieves the product from the product repository and updates its amount in the list.
   * Prints the updated orderItems list.
   *
   * @param choice      The choice of the product to add.
   * @param orderItems  The list of order items to update.
   */
  @Override
  public void addProduct(int choice, List<OrderItem> orderItems) {
    Product product = productRepository.findById(choice).get();

    debug("Selected product: " + product);

    updateProductAmount(orderItems, product);

    debug(orderItems.toString());
  }


  /**
   * This method updates the amount of a product in a list of order items.
   * If the product is not already in the list, a new order item is added with an amount of 1.
   * If the product is already in the list, the amount is incremented by 1.
   *
   * @param orderItems The list of order items.
   * @param product The product to update.
   */
  private void updateProductAmount(List<OrderItem> orderItems, Product product) {
    List<OrderItem> orderItemsWithProduct = orderItems.stream()
            .filter(orderItem -> orderItem.getProduct().getName().equals(product.getName()))
            .collect(Collectors.toList());
    if (orderItemsWithProduct.isEmpty()) {
      orderItems.add(new OrderItem(1, product));
    } else {
      OrderItem orderItem = orderItemsWithProduct.get(0);
      orderItem.setAmount(orderItem.getAmount() + 1);
    }

    debug("Updated order items " + orderItems);
  }

}



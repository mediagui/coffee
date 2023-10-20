/**
 * Java code for printing a receipt in the console.
 * The PrintConsoleReceiptUseCaseImpl class implements the PrintReceiptUseCase interface and is responsible for printing the receipt.
 * It uses the ApplyPromotionsUseCase to apply promotions to the order before printing.
 * The printRecepit method formats and prints the order details and calculates the total amount.
 */
package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Order;
import com.inatlas.domain.entity.Promotion;
import com.inatlas.util.LogUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Optional;

import static com.inatlas.util.LogUtil.debug;

@Component("receipt")
 @Profile("command-line-runner")
public class PrintConsoleReceiptUseCaseImpl implements PrintReceiptUseCase {
  private final ApplyPromotionsUseCase applyPromotionsUseCase;

  public PrintConsoleReceiptUseCaseImpl(ApplyPromotionsUseCase applyPromotionsUseCase) {
    this.applyPromotionsUseCase = applyPromotionsUseCase;
  }

  @Override
  public void print(Order order) {
    debug("Starting to apply promotions");
    Optional<Promotion> promotion = applyPromotionsUseCase.applyAndGetTheBestPromotion(order);
    if(promotion.isPresent()) {
      order.setItems(promotion.get().getOrderItems());
      LogUtil.info("Promotion applied: " + promotion.get().getName());
    }

    printReceipt(order);
  }

  private void printReceipt(Order order) {
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    System.out.println("Order:");
    System.out.println("-------------------------------------------");
    System.out.println("Description          Quantity         Total");
    System.out.println("-------------------------------------------");

    order.getItems().forEach(item -> {
      System.out.printf("%-20s %10d %8.2f €%n", item.getProduct().getName(), item.getAmount(), item.getTotal());
    });

    System.out.println("-------------------------------------------");
    System.out.printf("Total: %33s €%n ", decimalFormat.format(order.getTotal()));

    debug("Receipt printed");

  }
}

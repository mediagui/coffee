package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.OrderItem;
import com.inatlas.domain.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {ApplyPromotionForTotalProductsUseCaseImpl.class})
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class ApplyPromotionForTotalProductsUseCaseImplTest {


  @Autowired
  private ApplyPromotionForTotalProductsUseCaseImpl applyPromotionsForTotalProductsUseCase;

  @Test
  public void contextLoads() {
    assertNotNull(applyPromotionsForTotalProductsUseCase);
  }


  //Generate test cases for the method applyPromotion(List<OrderItem> orderItems) of the class ApplyPromotionForTotalProductsUseCaseImpl.
  //The test cases should cover the following scenarios:
  //1. The order contains more than 8 products.
  //2. The order contains 8 products.
  //3. The order contains less than 8 products.
  //4. The order contains 0 products.
  @ParameterizedTest(name = "The order contains {1} order items with 2 products in each order.")
  @MethodSource("generateOrderItemsCases")
  public void testApplyPromotionToOrder(List<OrderItem> orderItems, int numberOfOrderItems, double totalOrderValueWithDiscountExpected) {

    applyPromotionsForTotalProductsUseCase.applyPromotion(orderItems);


    //Calculate the total value of the order
    double orderValue = orderItems.stream().mapToDouble(OrderItem::getTotal).sum();
    //Round the value to 2 decimals to avoid rounding errors
    double totalValueCalculated = new BigDecimal(orderValue).setScale(2, RoundingMode.HALF_UP).doubleValue();

    assertEquals(totalOrderValueWithDiscountExpected, totalValueCalculated);

  }


  private static Stream<Arguments> generateOrderItemsCases() {
    return Stream.of(
            Arguments.of(generateOrderItems(5),5, 9.5),
            Arguments.of(generateOrderItems(4),4, 8.0),
            Arguments.of(generateOrderItems(3),3, 6.0),
            Arguments.of(generateOrderItems(0),0, 0d)
    );
  }

  private static  List<OrderItem> generateOrderItems(int i) {
    List<OrderItem> orderItems = new ArrayList<>();

    for (int j = 0; j < i; j++) {
      orderItems.add(new OrderItem(2, generateProduct(j)));
    }

    return orderItems;
  }

  private static Product generateProduct(int id) {
    return new Product(id, "Product " + id, 1d, false, "food");
  }


}

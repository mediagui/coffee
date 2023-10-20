package com.inatlas.util;

import com.inatlas.domain.entity.Order;
import com.inatlas.domain.entity.OrderItem;
import com.inatlas.domain.entity.Product;
import com.inatlas.domain.entity.Promotion;
import com.inatlas.domain.usecase.ApplyPromotionForFoodAndDrinksOver50UseCase;
import com.inatlas.domain.usecase.ApplyPromotionForLatteUseCase;
import com.inatlas.domain.usecase.ApplyPromotionForTotalProductsUseCase;
import com.inatlas.domain.usecase.PromotionUseCase;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

class PromotionUtilTest {


  public static final double DISCOUNT_5 = 0.05;
  public static final double LATTE_PROMO_UNIT_PRICE = 3d;
  public static final int NUMBER_OF_LATTES_TO_GET_A_FREE_ESPRESSO = 2;
  private Order order8Products;
  private Order orderOver50;
  private Order orderLattes;

  @Mock
  PromotionUseCase promotionUseCase;

  @Mock
  ApplyPromotionForTotalProductsUseCase applyPromotionForTotalProductsUseCase;
  @Mock
  ApplyPromotionForFoodAndDrinksOver50UseCase applyPromotionForFoodAndDrinksOver50UseCase;
  @Mock
  ApplyPromotionForLatteUseCase applyPromotionForLatteUseCase;


  AutoCloseable closeable;

  @BeforeEach
  void setUp() {
    closeable = MockitoAnnotations.openMocks(this);
    order8Products = generateOrderForMoreThan8Products();
    orderOver50 = generateOrderOver50();

    orderLattes = generateOrderForWithFreeEspressos();
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();

    order8Products = null;
    orderOver50 = null;

  }


  @Test
  public void testGetMostBeneficialPromotionWithPositiveCase() {
    // Positive test cases

    List<OrderItem> expectedAmountOver50Items = cloneOrderItemWithLattesReducedPriceTo3(orderOver50.getItems());
    List<OrderItem> expectedMoreThan8ProductsItems = cloneOrderItemsWith5PercentDiscount(order8Products.getItems());
    List<OrderItem> expectedLatteItems = cloneOrderItemsWithFreeEspressos(orderLattes.getItems());

    // Generate three possible promotions for the order with and amount over 50 €
    Optional<Promotion> expectedLattePromotion = Optional.of(new Promotion(170d, expectedLatteItems, applyPromotionForLatteUseCase));
    Optional<Promotion> expectedTotalProductsPromotion = Optional.of(new Promotion(44.31d, expectedMoreThan8ProductsItems, applyPromotionForTotalProductsUseCase));
    Optional<Promotion> expectedFoodAndDrinksOver50Promotion = Optional.of(new Promotion(93d, expectedAmountOver50Items, applyPromotionForFoodAndDrinksOver50UseCase));


    Optional<Promotion> promotionResult = PromotionUtil.getMostBeneficialPromotion(expectedLattePromotion, expectedFoodAndDrinksOver50Promotion, expectedTotalProductsPromotion);


    assertTrue(promotionResult.isPresent());
    Promotion expected = expectedTotalProductsPromotion.get();
    Promotion result = promotionResult.get();
    assertEquals(expected.getName(), result.getName());
    assertEquals(expected.getAmount(), result.getAmount());
    assertIterableEquals(expected.getOrderItems(), result.getOrderItems());

  }


  @Test
  public void testGetMostBeneficialPromotionWithNegativeCase() {
    // Positive test cases

    List<OrderItem> over50Items = cloneOrderItemWithLattesReducedPriceTo3(orderOver50.getItems());
    List<OrderItem> moreThan8Products = cloneOrderItemsWith5PercentDiscount(order8Products.getItems());
    List<OrderItem> latteItems = cloneOrderItemsWithFreeEspressos(orderLattes.getItems());

    // Generate three possible promotions for the order with and amount over 50 €
    Optional<Promotion> lattePromotion = Optional.of(new Promotion(170d, latteItems, applyPromotionForLatteUseCase));
    Optional<Promotion> totalProductsPromotion = Optional.of(new Promotion(44.31d, moreThan8Products, applyPromotionForTotalProductsUseCase));
    Optional<Promotion> foodAndDrinksOver50Promotion = Optional.of(new Promotion(93d, over50Items, applyPromotionForFoodAndDrinksOver50UseCase));


    Optional<Promotion> promotionResult = PromotionUtil.getMostBeneficialPromotion(lattePromotion, foodAndDrinksOver50Promotion, totalProductsPromotion);


    assertTrue(promotionResult.isPresent());
    Promotion expected = lattePromotion.get();
    Promotion result = promotionResult.get();
    assertNotEquals(expected.getName(), result.getName());
    assertNotEquals(expected.getAmount(), result.getAmount());

  }



  @Test
  @DisplayName("Test the case when the promotion is applied and returned")
  void testCheckAppliedPromotion() {

    when(promotionUseCase.applyPromotion(anyList())).thenReturn(true);

    List<OrderItem> expectedItems = cloneOrderItemsWith5PercentDiscount(order8Products.getItems());

    Promotion expectedPromotion = new Promotion(46.5d, expectedItems, promotionUseCase);

    Optional<Promotion> optPromotionResult = PromotionUtil.checkPromotion(promotionUseCase, order8Products.getItems());

    Promotion promotionResult = optPromotionResult.isPresent() ? optPromotionResult.get() : null;


    assertTrue(optPromotionResult.isPresent());
    assertIterableEquals(expectedPromotion.getOrderItems(), promotionResult.getOrderItems());
    assertEquals(expectedPromotion.getAmount(), promotionResult.getAmount());
  }


  @Test
  @DisplayName("Test the case when the promotion is not applied")
  void testCheckNotAppliedPromotion() {

    when(promotionUseCase.applyPromotion(order8Products.getItems())).thenReturn(false);

    Optional<Promotion> result = PromotionUtil.checkPromotion(promotionUseCase, order8Products.getItems());
    assertEquals(Optional.empty(), result);

  }


  @Test
  @DisplayName("Clone the order items and check that the cloned order items are equal to the original")
  void testCloneOrderItems() {
    List<OrderItem> result = PromotionUtil.cloneOrderItems(order8Products.getItems());
    assertIterableEquals(order8Products.getItems(), result);
  }


  @Test
  @DisplayName("Check that the total of the order is equal to the sum of the total of the order items")
  void testGetTotalOrderAmount() {
    double result = PromotionUtil.getTotalOrderAmount(order8Products.getItems());
    assertEquals(result, order8Products.getTotal());
  }


  // ***************************************
  // Utility methods to generate test data
  // ***************************************

  // Clone order items with free espressos calculated
  private List<OrderItem> cloneOrderItemsWithFreeEspressos(List<OrderItem> orderItems) {


    int lattes = (int) orderItems.stream()
            .filter(item -> !item.isPromotion() && item.getProduct().getName().endsWith("Latte"))
            .count();

    int previousFreeEspressos= (int) orderItems.stream()
            .filter(item -> !item.isPromotion() && item.getProduct().getName().endsWith("Espresso Gratis *"))
            .count();


    int freeLattes = lattes / NUMBER_OF_LATTES_TO_GET_A_FREE_ESPRESSO;
    
    if(previousFreeEspressos > 0){
      orderItems.stream().filter(item -> item.isPromotion() && item.getProduct().getName().endsWith("Espresso Gratis *"))
              .findFirst().get().setAmount(freeLattes);
    }else{
      orderItems.add(new OrderItem(freeLattes, generateFreeEspresso(orderItems.size()+1 )));
    }

    return new ArrayList<>(orderItems);

  }


  List<OrderItem> cloneOrderItemWithLattesReducedPriceTo3(List<OrderItem> orderItems) {

    // Clone order items with a unit price of 3 €
    return new ArrayList<>(
            orderItems.stream()
                    .filter(item -> !item.isPromotion())
                    .map(this::updatePriceWithValueOf3)
                    .collect(Collectors.toList())
    );
  }


  // Clone order items with a 5% discount applied
  List<OrderItem> cloneOrderItemsWith5PercentDiscount(List<OrderItem> orderItems) {


    // Clone order items with a 5% discount applied
    return new ArrayList<>(
            orderItems.stream()
                    .filter(item -> !item.isPromotion())
                    .map(this::updatePriceWithDiscount)
                    .collect(Collectors.toList())
    );
  }

  private OrderItem updatePriceWithValueOf3(OrderItem item) {
    item.setUnitPrice(LATTE_PROMO_UNIT_PRICE);
    return new OrderItem(item);
  }

  // Update the price of an order item after applying the discount
  private OrderItem updatePriceWithDiscount(OrderItem item) {
    double price = getRoundedUnitPriceWithDiscountFromOrderItem(item);
    item.setUnitPrice(price);
    return new OrderItem(item);
  }

  // Get the rounded unit price from an order item with a 5% discount
  private double getRoundedUnitPriceWithDiscountFromOrderItem(OrderItem item) {
    return BigDecimal.valueOf(item.getUnitPrice() / (1d + DISCOUNT_5))
            .setScale(NUMBER_OF_LATTES_TO_GET_A_FREE_ESPRESSO, RoundingMode.HALF_UP)
            .doubleValue();
  }


  private Order generateOrderForWithFreeEspressos() {
    // Generate 4 order items with 1 espresso each
    List<OrderItem> orderItems = new ArrayList<>(4);
    for (int i = 1; i <= 4; i++) {
      orderItems.add(generateOrderItem(i, 1, false, false));
    }

    for (int i = 1; i <= 4; i++) {
      orderItems.addAll(generateOrderItemsForFreeAndPaidEspressos());
    }

    return new Order(orderItems);
  }

  // Generate an order with 4 lattes and 5 paid espressos
  private List<OrderItem> generateOrderItemsForFreeAndPaidEspressos() {
    // Generate 4 order items with 1 espresso each
    List<OrderItem> orderItems = new ArrayList<>(4);
    for (int i = 1; i <= 4; i++) {
      // If we set that is not food and not a promotion, the order item will be a latte
      orderItems.add(generateOrderItem(i, 1, false, false));
    }

    for (int i = 0; i < 5; i++) {
      orderItems.add(generatePaidEspressoOrderItem(i));
    }

    return orderItems;
  }


  // Generate an order with 10 order items
  private Order generateOrderForMoreThan8Products() {
    // Generate 10 order items were 3 of them are promotions (based on the value of i%3)
    List<OrderItem> orderItems = new ArrayList<>(10);
    for (int i = 1; i <= 10; i++) {
      orderItems.add(generateOrderItem(i, 1, false, i % 3 == 0));
    }

    return new Order(orderItems);
  }

  // Generate an order with 20 order items and a total amount over 50
  private Order generateOrderOver50() {
    // Generate 20 order items were 6 of them are drinks (Lattes) (based on the value of i%3)
    List<OrderItem> orderItems = new ArrayList<>();
    for (int i = 1; i <= 20; i++) {
      orderItems.add(generateOrderItem(i, 1, false, i % 3 == 0));
    }

    return new Order(orderItems);
  }

  // Generate an order item where you can select the amount, if it is a promotion and if it is food
  private OrderItem generateOrderItem(int id, int amount, boolean isPromotion, boolean isFood) {
    Product product = generateProduct(id, isPromotion, isFood);
    return new OrderItem(1, product, isPromotion, true);
  }

  private OrderItem generatePaidEspressoOrderItem(int id) {
    return new OrderItem(1, generateEspresso(id), false, true);
  }

  // Generate a product where you can select the id, if it is a promotion and if it is food or drink
  private Product generateProduct(int id, boolean isPromotion, boolean isFood) {
    // Generate a product with a price of 5 € if it is food, 4.5 € if it is a drink
    // and a name of "Sandwich <id>" if it is food, "Latte <id>" if it is a drink
    // If the product is a promotion and drink the price is 3 €
    return new Product(id, (isFood ? "Sandwich " : "Latte ") + id, (isFood ? 5d : !isFood && isPromotion ? LATTE_PROMO_UNIT_PRICE : 4.5d), isPromotion, isFood ? "food" : "drink");
  }

  private Product generateEspresso(int id) {
    // Generate a product with a price of 5 € if it is food, 4.5 € if it is a drink
    // and a name of "Sandwich <id>" if it is food, "Latte <id>" if it is a drink
    return new Product(id, "Espresso " + id, 4d, false, "drink");
  }


  private Product generateFreeEspresso(int id) {
    return new Product(id, "Espresso Gratis *", 0d, true, "drink");
  }


}

// Tests End

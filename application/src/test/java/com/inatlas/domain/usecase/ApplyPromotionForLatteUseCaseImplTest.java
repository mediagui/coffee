package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.OrderItem;
import com.inatlas.domain.entity.Product;
import com.inatlas.domain.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class ApplyPromotionForLatteUseCaseImplTest {
  @Mock
  ProductRepository productRepository;
  @InjectMocks
  ApplyPromotionForLatteUseCaseImpl applyPromotionsForLatteUseCaseImpl;


  AutoCloseable closeable;

  @BeforeEach
  void setUp() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  // Generate test cases for the method applyPromotion(List<OrderItem> orderItems) of the class ApplyPromotionForLatteUseCaseImpl.
  // The test cases should cover the following scenarios:
  // 1. The order contains 0 lattes and added 4 espressos, so you will receive a number of free espressos that is the number of lattes/2
  // 2. The order contains 3 lattes and added 3 espressos, so you will receive 2 espressos, one of them is free.
  // 3. The order contains 4 lattes and added 2 espressos, so you will receive 2 free espressos.
  // 4. The order contains 5 lattes and added 1 espresso, so you will receive 3 free espressos.


  @ParameterizedTest(name = "The order contains {1} order items with 2 products in each order.")
  @MethodSource("generateOrderItemsCases")
  public void testApplyPromotionToOrder(List<OrderItem> orderItems, int numberOfOrderItems, OrderItem paidEspressos, OrderItem expectedToBePaidEspressos){

    when(productRepository.findByName("Latte")).thenReturn(Optional.of(generateLatte()));
    when(productRepository.findByName("Espresso")).thenReturn(Optional.of(generateEspresso(false)));


    orderItems.add(paidEspressos);

    applyPromotionsForLatteUseCaseImpl.applyPromotion(orderItems);
    
    //Test if the free Espressos are added to the order
    int numberOfFreeEspressos = orderItems.stream().filter(item -> item.getProduct().getName().endsWith("Gratis *")).findFirst()
            .orElseGet(() -> new OrderItem(0, new Product(0,null,0d,false,null))).getAmount();

    int numberOfExpectedFreeEspressos = numberOfOrderItems/2;
    Assertions.assertEquals(numberOfExpectedFreeEspressos, numberOfFreeEspressos);


    //Number of paid espressos
    int numberOfPaidEspressos = orderItems.stream().filter(item -> item.getProduct().getName().endsWith("Espresso")).findFirst()
            .orElseGet(() -> new OrderItem(0, new Product(0,null,0d,false,null))).getAmount();

    Assertions.assertEquals(expectedToBePaidEspressos.getAmount(), numberOfPaidEspressos);
  }


  private static Stream<Arguments> generateOrderItemsCases() {
    return Stream.of(
            Arguments.of(generateLatteOrderItems(5),5,
                    new OrderItem(1, generateEspresso(false)),
                    new OrderItem(0, generateEspresso(false))),
            Arguments.of(generateLatteOrderItems(4),4,
                    new OrderItem(2, generateEspresso(false)),
                    new OrderItem(0, generateEspresso(false))),
            Arguments.of(generateLatteOrderItems(3),3,
                    new OrderItem(3, generateEspresso(false)),
                    new OrderItem(2, generateEspresso(false))),
            Arguments.of(generateLatteOrderItems(0),0,
                    new OrderItem(4, generateEspresso(false)),
                    new OrderItem(4, generateEspresso(false)))
    );
  }

  private static  List<OrderItem> generateLatteOrderItems(int i) {
    List<OrderItem> orderItems = new ArrayList<>();

    for (int j = 0; j < i; j++) {
      orderItems.add(new OrderItem(1, generateLatte()));
    }

    //We need sum the products and group the orderItems by product
    Map<Product, Integer> productMap = orderItems.stream().collect(groupingBy(OrderItem::getProduct, summingInt(OrderItem::getAmount)));

    // We need to clear the orderItems list to add the new order items
    orderItems.clear();
    // Create a order item for each product in the map
    productMap.forEach((product, amount) -> orderItems.add(new OrderItem(amount, product)));

    int numerOfLattes = orderItems.stream().mapToInt(OrderItem::getAmount).sum();


    return orderItems;
  }


  private static Product generateLatte() {

    return new Product(1, "Latte", 5.3, false, "drink");

  }
  private static Product generateEspresso( boolean isPromotional) {

    double price = isPromotional ? 0d : 4d;

    return new Product(2, "Espresso", price, isPromotional, "drink");

  }


}

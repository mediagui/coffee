package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Order;
import com.inatlas.domain.entity.OrderItem;
import com.inatlas.domain.entity.Product;
import com.inatlas.domain.repository.OrderRepository;
import com.inatlas.util.CoffeeTestUtil;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OperationsOnOrderUseCaseImplTest {
  @Mock
  OrderRepository orderRepository;
  @InjectMocks
  OperationsOnOrderUseCaseImpl operationsOnOrderUseCaseImpl;

  AutoCloseable closeable;

  @BeforeEach
  void setUp() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }


  @Test
  @DisplayName("Test create new order")
  void testCreateNewOrder() {
    Order expectedOrder = new Order(CoffeeTestUtil.generateOrderItemList());
    when(orderRepository.createNewOrder()).thenReturn(expectedOrder);

    Order actualOrder = operationsOnOrderUseCaseImpl.createNewOrder();

    assertEquals(expectedOrder, actualOrder);
  }

  @Test
  @DisplayName("Test get order by ID with valid ID")
  void testGetOrderByIDWithValidID() {
    Integer id = 1;
    Order expectedOrder = new Order(CoffeeTestUtil.generateOrderItemList());

    when(orderRepository.getOrderByID(id)).thenReturn(Optional.of(expectedOrder));

    Optional<Order> actualOrder = operationsOnOrderUseCaseImpl.getOrderByID(id);

    assertTrue(actualOrder.isPresent());
    assertEquals(expectedOrder, actualOrder.get());
  }

  @Test
  @DisplayName("Test get order by ID with invalid ID")
  void testGetOrderByIDWithInvalidID() {
    Integer id = 2;
    when(orderRepository.getOrderByID(id)).thenReturn(Optional.empty());

    Optional<Order> actualOrder = operationsOnOrderUseCaseImpl.getOrderByID(id);

    assertFalse(actualOrder.isPresent());
  }

  @Test
  @DisplayName("Test set completed")
  void testSetCompleted() {
    Integer id = 1;

    operationsOnOrderUseCaseImpl.setCompleted(id);

    verify(orderRepository).setCompleted(id);
  }

  @Test
  @DisplayName("Test add product to order with valid product and amount")
  void testAddProductToOrderWithValidProductAndAmount() {
    Integer productId = 1;
    Integer amount = 2;
    Order expectedOrder = new Order(CoffeeTestUtil.generateOrderItemList());

    when(orderRepository.addProductToCurrentOrder(productId, amount)).thenReturn(Optional.of(expectedOrder));

    Optional<Order> actualOrder = operationsOnOrderUseCaseImpl.addProductToOrder(productId, amount);

    assertTrue(actualOrder.isPresent());
    assertEquals(expectedOrder, actualOrder.get());
  }

//  @Test
//  @DisplayName("Test add product to order with invalid product and amount")
//  void testAddProductToOrderWithInvalidProductAndAmount() {
//    Integer productId = 1;
//    Integer amount = 2;
//    when(orderRepository.addProductToCurrentOrder(productId, amount)).thenReturn(Optional.empty());
//
//    Optional<Order> actualOrder = operationsOnOrderUseCaseImpl.addProductToOrder(productId, amount);
//
//    assertFalse(actualOrder.isPresent());
//  }

  @Test
  @DisplayName("Test get actual order with existing order")
  void testGetActualOrderWithExistingOrder() {
    Order expectedOrder = new Order(CoffeeTestUtil.generateOrderItemList());

    when(orderRepository.getActualOrder()).thenReturn(Optional.of(expectedOrder));

    Optional<Order> actualOrder = operationsOnOrderUseCaseImpl.getActualOrder();

    assertTrue(actualOrder.isPresent());
    assertEquals(expectedOrder, actualOrder.get());
  }

  @Test
  @DisplayName("Test get actual order with no existing order")
  void testGetActualOrderWithNoExistingOrder() {
    when(orderRepository.getActualOrder()).thenReturn(Optional.empty());

    Optional<Order> actualOrder = operationsOnOrderUseCaseImpl.getActualOrder();

    assertFalse(actualOrder.isPresent());
  }
}

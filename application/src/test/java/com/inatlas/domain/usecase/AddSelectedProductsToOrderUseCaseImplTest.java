package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.OrderItem;
import com.inatlas.domain.entity.Product;
import com.inatlas.domain.repository.ProductRepository;
import com.inatlas.util.LogUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddSelectedProductsToOrderUseCaseImplTest {

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private AddSelectedProductsToOrderUseCaseImpl addSelectedProductsToOrderUseCase;

  @Mock
  private LogUtil logUtil;

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
  void testAddProduct_ExistingProduct() {
    int choice = 1;
    List<OrderItem> orderItems = new ArrayList<>();
    Product product = new Product(1, "Product A", 1.0,false, "food");

    when(productRepository.findById(choice)).thenReturn(Optional.of(product));

    addSelectedProductsToOrderUseCase.addProduct(choice, orderItems);

    assertEquals(1, orderItems.size());
    assertEquals(1, orderItems.get(0).getAmount());
    assertEquals(product, orderItems.get(0).getProduct());
    assertEquals(1.0, orderItems.get(0).getProduct().getPrice());
    assertFalse(orderItems.get(0).getProduct().isPromotion());
    assertEquals("food", orderItems.get(0).getProduct().getType());

  }
  @Test
  void testAddProduct_ExistingProduct_UpdateAmount() {
    int choice = 1;
    List<OrderItem> orderItems = new ArrayList<>();
    Product product = new Product(1, "Product A", 1.0,false, "food");

    when(productRepository.findById(choice)).thenReturn(Optional.of(product));

    addSelectedProductsToOrderUseCase.addProduct(choice, orderItems);
    addSelectedProductsToOrderUseCase.addProduct(choice, orderItems);

    assertEquals(1, orderItems.size());
    assertEquals(2, orderItems.get(0).getAmount());
    assertEquals(product, orderItems.get(0).getProduct());
    assertEquals(1.0, orderItems.get(0).getProduct().getPrice());
    assertFalse(orderItems.get(0).getProduct().isPromotion());
    assertEquals("food", orderItems.get(0).getProduct().getType());

  }


  @Test()
  void testAddProduct_NonExistingProduct() {
    int choice = 105;
    List<OrderItem> orderItems = new ArrayList<>();
    when(productRepository.findById(choice)).thenReturn(Optional.empty());
    assertThrows(NoSuchElementException.class, () -> addSelectedProductsToOrderUseCase.addProduct(choice, orderItems));

  }


}

package com.inatlas.infra.service;

import com.inatlas.domain.db.mapper.OrderDTOMapper;
import com.inatlas.domain.entity.Order;
import com.inatlas.domain.usecase.OperationsOnOrderUseCase;
import com.inatlas.domain.usecase.PayOrderUseCase;
import com.inatlas.infra.api.dto.OrderDTO;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.inatlas.infra.service.CoffeeTestUtil.*;
import static com.inatlas.infra.service.CoffeeTestUtil.generateOrderDTO;
import static com.inatlas.infra.service.CoffeeTestUtil.generateOrderItemList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderServiceTest {
  @Mock
  OrderDTOMapper orderDTOMapper;
  @Mock
  OperationsOnOrderUseCase operationsOnOrderUseCase;
  @InjectMocks
  OrderService orderService;
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
  @DisplayName("Test for adding a product to order")
  void testAddProductToOrder() {

    Integer productId = 1;
    Integer amount = 1;
    OrderDTO orderDTO = new OrderDTO(List.of(generateDrinkOrderItemDTO()),1d);
    Order order = new Order(List.of(generateDrinkOrderItem()));
    order.setTotal(1d);

    when(operationsOnOrderUseCase.addProductToOrder(productId, amount)).thenReturn(Optional.of(order));
    when(orderDTOMapper.toDTO(order)).thenReturn(orderDTO);

    ResponseEntity<OrderDTO> response = orderService.addProductToOrder(productId, amount);

    assertEquals(orderDTO, response.getBody());
  }

  @Test
  @DisplayName("Test por paying an order")
  void testPayOrder() {

    OrderDTO orderDTO = generateOrderDTO();

    when(orderDTOMapper.toDTO(any(Order.class))).thenReturn(orderDTO);
    when(operationsOnOrderUseCase.payOrder()).thenReturn(Optional.of(generateCompletedOrder()));

    ResponseEntity<OrderDTO> response = orderService.payOrder();


    assertEquals(orderDTO, response.getBody());
  }
}



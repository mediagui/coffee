package com.inatlas.infra.service;

import com.inatlas.domain.db.mapper.OrderDTOMapper;
import com.inatlas.domain.usecase.OperationsOnOrderUseCase;
import com.inatlas.domain.usecase.PayOrderUseCase;
import com.inatlas.infra.api.dto.OrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Service class that handles operations related to orders.
 */
@Service
public class OrderService {

  private final OrderDTOMapper orderDTOMapper;
  private final OperationsOnOrderUseCase operationsOnOrderUseCase;
  private final PayOrderUseCase payOrderUseCase;

  /**
   * Constructs an instance of OrderService with the provided dependencies.
   *
   * @param orderDTOMapper The mapper used to convert Order objects to DTOs.
   * @param operationsOnOrderUseCase The use case for performing operations on orders.
   * @param payOrderUseCase The use case for paying orders.
   */
  public OrderService(OrderDTOMapper orderDTOMapper, OperationsOnOrderUseCase operationsOnOrderUseCase, PayOrderUseCase payOrderUseCase) {
    this.orderDTOMapper = orderDTOMapper;
    this.operationsOnOrderUseCase = operationsOnOrderUseCase;
    this.payOrderUseCase = payOrderUseCase;
  }

  /**
   * Adds a product to the order with the specified product ID and amount.
   *
   * @param productId The ID of the product to add.
   * @param amount The amount of the product to add.
   * @return A ResponseEntity containing the order details as an OrderDTO.
   */
  public ResponseEntity<OrderDTO> addProductToOrder(Integer productId, Integer amount) {
    return ResponseEntity.ok(orderDTOMapper.toDTO(operationsOnOrderUseCase.addProductToOrder(productId, amount).get()));
  }

  /**
   * Initiates the payment process for the order.
   *
   * @return A ResponseEntity containing the updated order details as an OrderDTO.
   */
  public ResponseEntity<OrderDTO> payOrder() {
    return ResponseEntity.ok(payOrderUseCase.payOrder().map(orderDTOMapper::toDTO).get());
  }
}

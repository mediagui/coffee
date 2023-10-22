package com.inatlas.infra.controller;


import com.inatlas.domain.db.mapper.OrderDTOMapper;
import com.inatlas.domain.usecase.OperationsOnOrderUseCase;
import com.inatlas.domain.usecase.PayOrderUseCase;
import com.inatlas.infra.api.dto.OrderDTO;
import com.inatlas.infra.api.order.OrderControllerApi;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


@Controller
public class OrderController implements OrderControllerApi {


  private final OrderDTOMapper orderDTOMapper;
  private final OperationsOnOrderUseCase operationsOnOrderUseCase;
  private final PayOrderUseCase payOrderUseCase;

  public OrderController(OrderDTOMapper orderDTOMapper, OperationsOnOrderUseCase operationsOnOrderUseCase, PayOrderUseCase payOrderUseCase) {

    this.orderDTOMapper = orderDTOMapper;
    this.operationsOnOrderUseCase = operationsOnOrderUseCase;
    this.payOrderUseCase = payOrderUseCase;
  }

  @Override
  public ResponseEntity<OrderDTO> addProductToOrder(Integer productId, Integer amount) {
    return ResponseEntity.ok(orderDTOMapper.toDTO(operationsOnOrderUseCase.addProductToOrder(productId, amount).get()));
  }

  @Override
  public ResponseEntity<OrderDTO> payOrder() {
    return ResponseEntity.ok(payOrderUseCase.payOrder().map(orderDTOMapper::toDTO).get());
  }


}

package com.inatlas.infra.controller;


import com.inatlas.domain.db.mapper.OrderDTOMapper;
import com.inatlas.domain.usecase.OperationsOnOrderUseCase;
import com.inatlas.infra.api.dto.OrderDTO;
import com.inatlas.infra.api.order.OrderControllerApi;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


@Controller
public class OrderController implements OrderControllerApi {


  private final OrderDTOMapper orderDTOMapper;
  private final OperationsOnOrderUseCase operationsOnOrderUseCase;

  public OrderController(OrderDTOMapper orderDTOMapper, OperationsOnOrderUseCase operationsOnOrderUseCase) {

    this.orderDTOMapper = orderDTOMapper;
    this.operationsOnOrderUseCase = operationsOnOrderUseCase;
  }

  @Override
  public ResponseEntity<OrderDTO> addProductToOrder(Integer productId, Integer amount) {
    return ResponseEntity.ok(orderDTOMapper.toDTO(operationsOnOrderUseCase.addProductToOrder(productId, amount).get()));
  }

  @Override
  public ResponseEntity<OrderDTO> createNewOrder() {
    return  ResponseEntity.ok( orderDTOMapper.toDTO(operationsOnOrderUseCase.createNewOrder())) ;
  }

//

}

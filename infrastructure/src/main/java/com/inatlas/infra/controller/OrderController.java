package com.inatlas.infra.controller;


import com.inatlas.infra.api.dto.OrderDTO;
import com.inatlas.infra.api.order.OrderControllerApi;
import com.inatlas.infra.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


@Controller
public class OrderController implements OrderControllerApi {

  private final OrderService orderService;
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @Override
  public ResponseEntity<OrderDTO> addProductToOrder(Integer productId, Integer amount) {
    return orderService.addProductToOrder(productId, amount);
  }

  @Override
  public ResponseEntity<OrderDTO> payOrder() {
    return orderService.payOrder();
  }


}

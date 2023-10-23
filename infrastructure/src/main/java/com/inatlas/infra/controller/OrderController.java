package com.inatlas.infra.controller;


import com.inatlas.infra.api.dto.OrderDTO;
import com.inatlas.infra.api.order.OrderControllerApi;
import com.inatlas.infra.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


/**
 * Controller class that handles operations related to orders.
 */
@Controller
public class OrderController implements OrderControllerApi {

  private final OrderService orderService;
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  /**
   * Adds a product to the order.
   * @param productId Product id (required)
   * @param amount Amount of product (required)
   * @return A ResponseEntity containing the response details as a OrderDTO with the new product added..
   */
  @Override
  public ResponseEntity<OrderDTO> addProductToOrder(Integer productId, Integer amount) {
    return orderService.addProductToOrder(productId, amount);
  }

  /**
   * Pay the order.
   * @return A ResponseEntity containing the response details as a OrderDTO.
   */
  @Override
  public ResponseEntity<OrderDTO> payOrder() {
    return orderService.payOrder();
  }


}

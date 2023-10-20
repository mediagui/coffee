package com.inatlas.infra.controller;

import com.inatlas.infra.api.ApiCoffeeShopController;
import com.inatlas.infra.api.ApiCoffeeShopDelegate;
import com.inatlas.infra.dto.OrderDTO;
import com.inatlas.infra.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;


public class OrderController extends ApiCoffeeShopController {

  private OrderService delegate;

  public OrderController(ApiCoffeeShopDelegate delegate) {
    super(delegate);
  }

  @Override
  public ResponseEntity<OrderDTO> createNewOrder() {
    return super.createNewOrder();
  }


  @Override
  public ResponseEntity<OrderDTO> addProductToOrder(Integer id, Integer amount) {
    return super.addProductToOrder(id, amount);
  }


}

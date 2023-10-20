package com.inatlas.infra.controller;

import com.inatlas.infra.api.ApiCoffeeShopController;
import com.inatlas.infra.api.ApiCoffeeShopDelegate;
import com.inatlas.infra.dto.OrderDTO;
import com.inatlas.infra.service.ReceiptService;
import org.springframework.http.ResponseEntity;

public class ReceiptController extends ApiCoffeeShopController {

  private ReceiptService delegate;

  public ReceiptController(ApiCoffeeShopDelegate delegate) {
    super(delegate);
  }

  @Override
  public ResponseEntity<OrderDTO> getReceipt(String format) {
    return super.getReceipt(format);
  }
}

package com.inatlas.infra.service;

import com.inatlas.infra.api.ApiCoffeeShopDelegate;
import com.inatlas.infra.dto.OrderDTO;
import org.springframework.http.ResponseEntity;

public class ReceiptService implements ApiCoffeeShopDelegate {

  @Override
  public ResponseEntity<OrderDTO> getReceipt(String format) {
    return ApiCoffeeShopDelegate.super.getReceipt(format);
  }
}

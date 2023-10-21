package com.inatlas.infra.service;

import com.inatlas.infra.api.ApiCoffeeShopDelegate;
import com.inatlas.infra.dto.OrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService implements ApiCoffeeShopDelegate {

  @Override
  public ResponseEntity<OrderDTO> getReceipt(String format) {
    return ApiCoffeeShopDelegate.super.getReceipt(format);
  }
}

package com.inatlas.infra.controller;

import com.inatlas.infra.service.ProductsService;
import com.inatlas.infra.api.ApiCoffeeShopController;
import com.inatlas.infra.api.ApiCoffeeShopDelegate;
import com.inatlas.infra.dto.OrderDTO;
import com.inatlas.infra.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;


public class ProductsController extends ApiCoffeeShopController {

  private ProductsService delegate;

  public ProductsController(ApiCoffeeShopDelegate delegate) {
    super(delegate);
  }

  @Override
  public ResponseEntity<OrderDTO> getActualOrder() {
    return super.getActualOrder();
  }

  @Override
  public ResponseEntity<ResponseDTO> getAllProducts() {
    return super.getAllProducts();
  }

  @Override
  public ResponseEntity<ResponseDTO> getProductById(Integer id) {
    return super.getProductById(id);
  }



  @Override
  public ResponseEntity<OrderDTO> getReceipt(String format) {
    return super.getReceipt(format);
  }
}

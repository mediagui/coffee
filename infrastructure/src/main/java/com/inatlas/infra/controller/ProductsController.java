package com.inatlas.infra.controller;

import com.inatlas.infra.api.dto.ResponseDTO;
import com.inatlas.infra.api.product.ProductsControllerApi;
import com.inatlas.infra.service.ProductsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


/**
 * Controller class that handles operations related to products.
 */
@Controller
public class ProductsController  implements ProductsControllerApi {

  private final ProductsService productsService;


  public ProductsController(ProductsService productsService) {
      this.productsService = productsService;

  }

  /**
   * Retrieves all products.
   * @return A ResponseEntity containing the response details as a ResponseDTO.
   */
  @Override
  public ResponseEntity<ResponseDTO> getAllProducts() {
    return productsService.getAllProducts();
  }

  /**
   * Retrieves a product by its ID.
   * @param id Product id (required)
   * @return A ResponseEntity containing the response details as a ResponseDTO.
   */
  @Override
  public ResponseEntity<ResponseDTO> getProductById(Integer id) {
    return productsService.getProductById(id);
  }


}

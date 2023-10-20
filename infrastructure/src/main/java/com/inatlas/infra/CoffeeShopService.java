package com.inatlas.infra;

import com.inatlas.domain.db.mapper.ProductDTOMapper;
import com.inatlas.domain.entity.Product;
import com.inatlas.domain.usecase.GetAllProductsUseCase;
import com.inatlas.domain.usecase.GetOneProductUseCase;
import com.inatlas.infra.api.ApiCoffeeShopDelegate;
import com.inatlas.infra.dto.OrderDTO;
import com.inatlas.infra.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.EMPTY_LIST;

@Service
public class CoffeeShopService implements ApiCoffeeShopDelegate {

  private final GetOneProductUseCase getOneProductUseCase;
  private final GetAllProductsUseCase getAllProductsUseCase;
  private final ProductDTOMapper productDTOMapper;


  public CoffeeShopService(GetOneProductUseCase getOneProductUseCase, GetAllProductsUseCase getAllProductsUseCase, ProductDTOMapper productDTOMapper) {
    this.getOneProductUseCase = getOneProductUseCase;
    this.getAllProductsUseCase = getAllProductsUseCase;
    this.productDTOMapper = productDTOMapper;
  }

  @Override
  public ResponseEntity<OrderDTO> getActualOrder() {
    return ApiCoffeeShopDelegate.super.getActualOrder();
  }

  @Override
  public ResponseEntity<ResponseDTO> getAllProducts() {

    List<Product> products = getAllProductsUseCase.getAllProducts().orElseGet(() -> EMPTY_LIST);

    HttpStatus status = products.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;

    ResponseDTO responseDTO = new ResponseDTO();
    responseDTO.setProducts(productDTOMapper.toDTOList(products));
    return ResponseEntity.status(status).body(responseDTO);

  }

  @Override
  public ResponseEntity<ResponseDTO> getProductById(Long id) {
    return ApiCoffeeShopDelegate.super.getProductById(id);
  }

}

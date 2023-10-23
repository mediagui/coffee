package com.inatlas.infra.service;

import com.inatlas.domain.db.mapper.ProductDTOMapper;
import com.inatlas.domain.entity.Product;
import com.inatlas.domain.usecase.GetAllProductsUseCase;
import com.inatlas.domain.usecase.GetOneProductUseCase;
import com.inatlas.infra.api.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.EMPTY_LIST;

/**
 * Service class that handles operations related to products.
 */
@Service
public class ProductsService {

  private final GetOneProductUseCase getOneProductUseCase;
  private final GetAllProductsUseCase getAllProductsUseCase;
  private final ProductDTOMapper productDTOMapper;
  private ResponseDTO responseDTO;

  /**
   * Constructs an instance of ProductsService with the provided dependencies.
   *
   * @param getOneProductUseCase The use case for retrieving a single product.
   * @param getAllProductsUseCase The use case for retrieving all products.
   * @param productDTOMapper The mapper used to convert Product objects to DTOs.
   */
  public ProductsService(GetOneProductUseCase getOneProductUseCase, GetAllProductsUseCase getAllProductsUseCase, ProductDTOMapper productDTOMapper) {
    this.getOneProductUseCase = getOneProductUseCase;
    this.getAllProductsUseCase = getAllProductsUseCase;
    this.productDTOMapper = productDTOMapper;
  }

  /**
   * Retrieves all products.
   *
   * @return A ResponseEntity containing the response details as a ResponseDTO.
   */
  public ResponseEntity<ResponseDTO> getAllProducts() {
    List<Product> products = getAllProductsUseCase.getAllProducts().orElseGet(Collections::emptyList);
    HttpStatus status = products.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;

    // We make sure that the response is always a new object
    responseDTO = new ResponseDTO();
    responseDTO.setProducts(productDTOMapper.toDTOList(products));

    return ResponseEntity.status(status).body(responseDTO);
  }

  /**
   * Retrieves a product by its ID.
   *
   * @param id The ID of the product to retrieve.
   * @return A ResponseEntity containing the response details as a ResponseDTO.
   */
  public ResponseEntity<ResponseDTO> getProductById(Integer id) {
    Optional<Product> product = getOneProductUseCase.getTheProduct(id);
    HttpStatus status = product.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;

    // We make sure that the response is always a new object
    responseDTO = new ResponseDTO();
    responseDTO.setProducts(Collections.singletonList(productDTOMapper.toDTO(product.orElse(null))));

    return ResponseEntity.status(status).body(responseDTO);
  }
}

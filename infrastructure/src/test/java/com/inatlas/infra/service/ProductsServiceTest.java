package com.inatlas.infra.service;

import com.inatlas.domain.db.mapper.ProductDTOMapper;
import com.inatlas.domain.entity.Product;
import com.inatlas.domain.usecase.GetAllProductsUseCase;
import com.inatlas.domain.usecase.GetOneProductUseCase;
import com.inatlas.infra.api.dto.ProductDTO;
import com.inatlas.infra.api.dto.ResponseDTO;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.EMPTY_LIST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

class ProductsServiceTest {
  @Mock
  GetOneProductUseCase getOneProductUseCase;
  @Mock
  GetAllProductsUseCase getAllProductsUseCase;
  @Mock
  ProductDTOMapper productDTOMapper;
  @Mock
  ResponseDTO responseDTO;
  @InjectMocks
  ProductsService productsService;

  AutoCloseable closeable;

  @BeforeEach
  void setUp() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }


  @Test
  @DisplayName("Returning a empty list and expecting a 404 status code")
  void testGetAllProducts_WithEmptyList_ReturnsNotFound() {
    when(getAllProductsUseCase.getAllProducts()).thenReturn(Optional.empty());

    ResponseEntity<ResponseDTO> response = productsService.getAllProducts();

    assertEquals(NOT_FOUND, response.getStatusCode());
    assertEquals(EMPTY_LIST, response.getBody().getProducts());
  }

  @Test
  @DisplayName("Returning a non empty list but an empty Product and expecting a 200 status code")
  void testGetAllProducts_WithNonEmptyListButEmptyProduct_ReturnsOk() {
    List<Product> productList = CoffeeTestUtil.generateProductList();

    when(getAllProductsUseCase.getAllProducts()).thenReturn(Optional.of(productList));

    List<ProductDTO> productDTOList = Collections.singletonList(new ProductDTO());
    when(productDTOMapper.toDTOList(productList)).thenReturn(productDTOList);

    ResponseEntity<ResponseDTO> response = productsService.getAllProducts();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(productDTOList, response.getBody().getProducts());
  }

  @Test
  @DisplayName("Returning a non empty list but an empty Product and expecting a 200 status code")
  void testGetAllProducts_WithNonEmptyList_ReturnsOk() {

    List<Product> productList = Collections.singletonList(new Product());
    when(getAllProductsUseCase.getAllProducts()).thenReturn(Optional.of(productList));

    List<ProductDTO> productDTOList = Collections.singletonList(new ProductDTO());
    when(productDTOMapper.toDTOList(productList)).thenReturn(productDTOList);

    ResponseEntity<ResponseDTO> response = productsService.getAllProducts();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(productDTOList, response.getBody().getProducts());
  }


  @Test
  void testGetProductById_WithExistingId_ReturnsOk() {
    int productId = 1;
    Product product = CoffeeTestUtil.generateDrinkProduct();

    when(getOneProductUseCase.getTheProduct(productId)).thenReturn(Optional.of(product));

    ProductDTO productDTO = CoffeeTestUtil.generateProductDTO(1);
    when(productDTOMapper.toDTO(product)).thenReturn(productDTO);

    ResponseEntity<ResponseDTO> response = productsService.getProductById(productId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(productDTO, response.getBody().getProducts().get(0));
  }

  @Test
  void testGetProductById_WithNonExistingId_ReturnsNotFound() {
    int productId = 100;
    when(getOneProductUseCase.getTheProduct(productId)).thenReturn(Optional.empty());

    ResponseEntity<ResponseDTO> response = productsService.getProductById(productId);

    assertEquals(NOT_FOUND, response.getStatusCode());
    assertEquals(null, response.getBody().getProducts().get(0));
  }

}

// Tests End

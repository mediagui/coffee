package com.inatlas.infra.controller;

import com.inatlas.infra.api.dto.ErrorDTO;
import com.inatlas.infra.api.dto.ProductDTO;
import com.inatlas.infra.api.dto.ResponseDTO;
import com.inatlas.infra.service.CoffeeTestUtil;
import com.inatlas.infra.service.ProductsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductsController.class)
@ContextConfiguration(classes = {ProductsController.class, ProductsService.class})
class ProductsControllerIntegrationTest {


  @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductsService productsService;


    @DisplayName("Check the response is a 500 code")
    @Test
    void testGetAllProducts_WithEmptyList_ReturnsInternalServerError() throws Exception {

      final ResponseDTO responseDTO = getErrorResponseDTO(INTERNAL_SERVER_ERROR);

      when(productsService.getAllProducts())
              .thenReturn(ResponseEntity.status(INTERNAL_SERVER_ERROR)
                      .contentType(MediaType.APPLICATION_JSON)
                      .body(responseDTO));

      mockMvc.perform(get("/api/v1/coffeeShop/products"))
              .andExpect(status().isInternalServerError())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.products").doesNotExist())
              .andExpect(jsonPath("$.error.message").value("Internal Server Error"));

    }

  private static ResponseDTO getErrorResponseDTO(HttpStatus internalServerError) {
    ErrorDTO errorDTO = new ErrorDTO(internalServerError.value(), internalServerError.getReasonPhrase());
    ResponseDTO responseDTO = new ResponseDTO();
    responseDTO.setError(errorDTO);
    return responseDTO;
  }


  @DisplayName("Check that the response is a 404 when the product is not found")
    @Test
    void testGetProductById_WithNotFound_ReturnsNotFound() throws Exception {

    final ResponseDTO responseDTO = getErrorResponseDTO(NOT_FOUND);

    when(productsService.getProductById(1000))
              .thenReturn(ResponseEntity.status(NOT_FOUND)
                      .contentType(MediaType.APPLICATION_JSON)
                      .body(responseDTO));

      mockMvc.perform(get("/api/v1/coffeeShop/product/1000"))
              .andExpect(status().isNotFound())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.product").doesNotExist())
              .andExpect(jsonPath("$.error.message").value("Not Found"));

    }

    @DisplayName("Check that the response is a 404 when the list is empty")
    @Test
    void testGetAllProducts_WithEmptyList_ReturnsNotFound() throws Exception {

      final ResponseDTO responseDTO = getErrorResponseDTO(NOT_FOUND);

      when(productsService.getAllProducts()).thenReturn(ResponseEntity.status(NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(responseDTO));

      mockMvc.perform(get("/api/v1/coffeeShop/products"))
              .andExpect(status().isNotFound())
              .andExpect(jsonPath("$.products").doesNotExist())
              .andExpect(jsonPath("$.error.message").value("Not Found"));

    }

    @DisplayName("Check that the response is an array of products and contains all the products")
    @Test
    void testGetAllProducts_ProductsController() throws Exception {


      List<ProductDTO> productList = CoffeeTestUtil.generateProductDTOList(2);
      ResponseDTO responseDTO = new ResponseDTO();
      responseDTO.setProducts(productList);

      when(productsService.getAllProducts()).thenReturn(ResponseEntity.ok(responseDTO));

      mockMvc.perform(get("/api/v1/coffeeShop/products"))
              .andExpect(status().isOk())
              .andExpect(content().contentType("application/json"))
              .andExpect(jsonPath("$.products").isArray())
              .andExpect(jsonPath("$.products[0].name").value("Product 1"))
              .andExpect(jsonPath("$.products[0].type").value("Food"))
              .andExpect(jsonPath("$.products[1].name").value("Product 2"))
              .andExpect(jsonPath("$.products[1].type").value("Drink"));

    }

    @Test
    void testGetProductById_ProductsController() throws Exception {

      ProductDTO productDTO = CoffeeTestUtil.generateProductDTO(1);
      ResponseDTO responseDTO = new ResponseDTO();
      responseDTO.addProductsItem(productDTO);

      when(productsService.getProductById(1)).thenReturn(ResponseEntity.ok(responseDTO));

      mockMvc.perform(get("/api/v1/coffeeShop/product/1"))
              .andExpect(status().isOk())
              .andExpect(content().contentType("application/json"))
              .andExpect(jsonPath("$.products[0].name").value("Product 1"))
              .andExpect(jsonPath("$.products[0].type").value("Food"));

    }
}

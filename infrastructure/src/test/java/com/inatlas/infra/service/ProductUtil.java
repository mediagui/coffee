package com.inatlas.infra.service;

import com.inatlas.domain.entity.Product;
import com.inatlas.infra.dto.ProductDTO;

import java.util.List;

public class ProductUtil {

  public static Product generateDrinkProduct(){
    return new Product(1, "Latte", 1.0, false, "Drink");

  }
  public static Product generateFoodProduct(){
    return new Product(2, "Sandwich", 1.0, false, "Food");

  }

  public static ProductDTO generateProductDTO( int... i ){
    int id = i.length == 0 ? 1: i[0];
    return new ProductDTO(id, "Product " + id, 1.0, id%2==0?"Drink":"Food", false );
  }

  public static List<ProductDTO> generateProductDTOList(int elements){
    return List.of(generateProductDTO(), generateProductDTO(elements));
  }
  public static List<Product> generateProductList(){
    return List.of(generateDrinkProduct(),generateFoodProduct());
  }

}

package com.inatlas.infra.service;

import com.inatlas.domain.entity.Product;

import java.util.List;

public class ProductUtil {

  public static Product generateDrinkProduct(boolean isPromotion){
    return new Product(1, "Latte", 1.0, isPromotion, "Drink");

  }
  public static Product generateFoodProduct(boolean isPromotion){
    return new Product(2, "Sandwich", 1.0, isPromotion, "Food");

  }

  public static List<Product> generateProductList(){
    return List.of(generateDrinkProduct(false), generateFoodProduct(false));
  }


}

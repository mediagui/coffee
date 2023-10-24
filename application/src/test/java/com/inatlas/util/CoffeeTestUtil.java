package com.inatlas.util;

import com.inatlas.domain.entity.OrderItem;
import com.inatlas.domain.entity.Product;

import java.util.List;

public class CoffeeTestUtil {

  public static Product generateDrinkProduct(){
    return new Product(1, "Latte", 1.0, false, "Drink");

  }
  public static Product generateFoodProduct(){
    return new Product(2, "Sandwich", 1.0, false, "Food");

  }


  public static List<Product> generateProductList(){
    return List.of(generateDrinkProduct(),generateFoodProduct());
  }

  public static List<OrderItem> generateOrderItemList(){
    return List.of(new OrderItem(1,generateDrinkProduct()), new OrderItem(1, generateFoodProduct()));
  }

}

package com.inatlas.infra.service;

import com.inatlas.domain.entity.Order;
import com.inatlas.domain.entity.OrderItem;
import com.inatlas.domain.entity.Product;
import com.inatlas.infra.api.dto.OrderDTO;
import com.inatlas.infra.api.dto.OrderItemDTO;
import com.inatlas.infra.api.dto.ProductDTO;

import java.time.LocalDateTime;
import java.util.List;

public class CoffeeTestUtil {

  public static Product generateDrinkProduct(){
    return new Product(1, "Latte", 1.0, false, "Drink");

  }
  public static Product generateFoodProduct(){
    return new Product(2, "Sandwich", 1.0, false, "Food");

  }

  public static ProductDTO generateDrinkProductDTO(){
    return new ProductDTO(1, "Latte", 1.0,  "Drink");
  }
  public static ProductDTO generateFoodProductDTO(){
    return new ProductDTO(2, "Sandwich", 1.0, "Food");
  }

  public static ProductDTO generateProductDTO( int... i ){
    int id = i.length == 0 ? 1: i[0];
    ProductDTO productDTO = new ProductDTO();
    productDTO.setId(id);
    productDTO.setName("Product " + id);
    productDTO.setPrice(1.0);
    productDTO.setType(id%2==0?"Drink":"Food");
    productDTO.setIsPromotion(false);
    return productDTO;

//    return new ProductDTO(id, "Product " + id, 1.0, id%2==0?"Drink":"Food", false );
  }

  public static List<ProductDTO> generateProductDTOList(int elements){
    return List.of(generateProductDTO(), generateProductDTO(elements));
  }
  public static List<Product> generateProductList(){
    return List.of(generateDrinkProduct(),generateFoodProduct());
  }

  public static List<OrderItem> generateOrderItemList(){
    return List.of(new OrderItem(1,generateDrinkProduct()), new OrderItem(1, generateFoodProduct()));
  }

  public static OrderItem generateDrinkOrderItem(){
    return new OrderItem(1,generateDrinkProduct());
  }

  public static OrderItem generateFoodOrderItem(){
    return new OrderItem(1,generateFoodProduct());
  }
  public static OrderItemDTO generateDrinkOrderItemDTO(){
    return new OrderItemDTO(generateDrinkProductDTO(),1,1d,1d);
  }

  public static OrderItemDTO generateFoodOrderItemDTO(){
    return new OrderItemDTO(generateFoodProductDTO(),1,1d,1d);
  }

  public static List<OrderItemDTO> generateOrderItemDTOList(){

    return List.of(
            new OrderItemDTO(generateDrinkProductDTO(), 1, 1d, 1d),
            new OrderItemDTO(generateFoodProductDTO(), 1, 1d, 1d)
    );
  }


  public static OrderDTO generateOrderDTO(){

    return new OrderDTO(generateOrderItemDTOList(),2d);

  }

  public static Order generateCompletedOrder() {
    Order order = new Order(generateOrderItemList());
    order.setComplete(true);
    order.setOrderDate(LocalDateTime.parse("2021-09-01T12:00:00.000"));
    order.setTotal(2d);

    return order;
  }
}

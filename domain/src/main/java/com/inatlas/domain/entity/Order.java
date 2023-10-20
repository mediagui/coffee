package com.inatlas.domain.entity;

import java.util.List;
import java.util.StringJoiner;

public class Order {

  private List<OrderItem> items;

  public Order(List<OrderItem> items) {
    this.items = items;
  }

  public List<OrderItem> getItems() {
    return items;
  }

  public void setItems(List<OrderItem> items) {
    this.items = items;
  }

  public double getTotal() {

    return items.stream().mapToDouble(OrderItem::getTotal).sum();

  }


  @Override
  public String toString() {
    return new StringJoiner(", ", Order.class.getSimpleName() + "[", "]")
            .add("items=" + items)
            .toString();
  }
}

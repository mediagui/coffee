package com.inatlas.domain.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.StringJoiner;
public class Order {

  private Integer id;

  private LocalDateTime orderDate;
  private boolean complete;
  private List<OrderItem> items;
  private double total;

  private String promotion;

  public Order(List<OrderItem> items) {
    this.items = items;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public LocalDateTime getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(LocalDateTime orderDate) {
    this.orderDate = orderDate;
  }

  public boolean isComplete() {
    return complete;
  }

  public void setComplete(boolean complete) {
    this.complete = complete;
  }

  public List<OrderItem> getItems() {
    return items;
  }

  public void setItems(List<OrderItem> items) {
    this.items = items;
  }

  public void setTotal(double total) {
    this.total = total;
  }
  public double getTotal() {
    return items.stream().mapToDouble(OrderItem::getTotal).sum();

  }


  public String getPromotion() {
    return promotion;
  }

  public void setPromotion(String promotion) {
    this.promotion = promotion;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Order.class.getSimpleName() + "[", "]")
            .add("items=" + items)
            .toString();
  }
}

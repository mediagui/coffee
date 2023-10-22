package com.inatlas.domain.db.entity;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class OrderDB {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private double total;
  private LocalDateTime orderDate;
  private boolean complete;
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "orderId")
  private List<OrderItemDB> items;
  public OrderDB(){}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
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

  public List<OrderItemDB> getItems() {
    return items;
  }

  public void setItems(List<OrderItemDB> items) {
    this.items = items;
  }

  public OrderDB(Integer id, double total) {
    this.id = id;
    this.total = total;
    this.orderDate = LocalDateTime.now();
  }
}

package com.inatlas.domain.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class OrderItem {

  @Id
  private int id;
  private int amount;
  @OneToOne
  @JoinColumn(name = "product_id")
  private ProductDB productDB;
  private double unitPrice;
  private double total;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private OrderDB orderDB;

  public OrderDB getOrder() {
    return orderDB;
  }

  public void setOrder(OrderDB orderDB) {
    this.orderDB = orderDB;
  }

  public OrderItem() {
  }

  public OrderItem(int amount, ProductDB productDB, double unitPrice, double total) {
    this.amount = amount;
    this.productDB = productDB;
    this.unitPrice = unitPrice;
    this.total = total;
  }


}

package com.inatlas.domain.db.entity;

import javax.persistence.*;

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
  private Orders orders;

  public Orders getOrder() {
    return orders;
  }

  public void setOrder(Orders orders) {
    this.orders = orders;
  }

  public OrderItem() {
  }

  public OrderItem(int amount, ProductDB productDB, double unitPrice, double total) {
    this.amount = amount;
    this.productDB = productDB;
    this.unitPrice = unitPrice;
    this.total = total;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public ProductDB getProduct() {
    return productDB;
  }

  public void setProduct(ProductDB productDB) {
    this.productDB = productDB;
  }

  public double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }
}

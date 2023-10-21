package com.inatlas.domain.db.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class OrderItemDB {

  @Id
  private Integer id;
  private int amount;
  @OneToOne
  @JoinColumn(name = "product_id")
  private ProductDB product;
  private double unitPrice;
  private double total;
  @ManyToOne
  @JoinColumn(name = "order_id")
  private OrderDB orderDB;

  public OrderItemDB() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public ProductDB getProduct() {
    return product;
  }

  public void setProduct(ProductDB product) {
    this.product = product;
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

  public OrderDB getOrderDB() {
    return orderDB;
  }

  public void setOrderDB(OrderDB orderDB) {
    this.orderDB = orderDB;
  }


  public OrderItemDB(int amount, ProductDB product, double unitPrice, double total) {
    this.amount = amount;
    this.product = product;
    this.unitPrice = unitPrice;
    this.total = total;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OrderItemDB)) return false;
    OrderItemDB that = (OrderItemDB) o;
    return getAmount() == that.getAmount() && Double.compare(getUnitPrice(), that.getUnitPrice()) == 0 && Double.compare(getTotal(), that.getTotal()) == 0 && Objects.equals(getId(), that.getId()) && Objects.equals(getProduct(), that.getProduct()) && Objects.equals(getOrderDB(), that.getOrderDB());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getAmount(), getProduct(), getUnitPrice(), getTotal(), getOrderDB());
  }
}

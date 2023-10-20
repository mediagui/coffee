package com.inatlas.domain.entity;

import java.util.Objects;
import java.util.StringJoiner;

public class OrderItem{
  private int amount;
  private Product product;
  private double unitPrice;

  private boolean isNew;

  private boolean isPromotion;

  public OrderItem(int amount, Product product) {
    this.amount = amount;
    this.product = product;
    this.unitPrice = product.getPrice();
    this.isPromotion=false;
  }

  public OrderItem(int amount, Product product, boolean isPromotion) {
    this.amount = amount;
    this.product = product;
    this.unitPrice = isPromotion||product.isPromotion() ? 0 : product.getPrice();
    this.isPromotion = isPromotion;

  }

  public OrderItem(OrderItem other){
    this(other.getAmount(), other.getProduct(), other.isPromotion(), other.isNew());
  }

  public OrderItem(int amount, Product product, boolean isPromotion, boolean isNew) {
    this(amount,product,isPromotion);
    this.isNew = isNew;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public boolean isPromotion() {
    return isPromotion;
  }

  public void setPromotion(boolean promotion) {
    isPromotion = promotion;
  }

  public double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
  }

  public double getTotal() {
    return amount * unitPrice;
  }

  public boolean isNew() {
    return isNew;
  }

  public void setNew(boolean aNew) {
    isNew = aNew;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", OrderItem.class.getSimpleName() + "[", "]")
            .add("amount=" + amount)
            .add("product=" + product)
            .add("total=" + getTotal())
            .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OrderItem)) return false;
    OrderItem orderItem = (OrderItem) o;
    return getAmount() == orderItem.getAmount() && Double.compare(getUnitPrice(), orderItem.getUnitPrice()) == 0 && isNew() == orderItem.isNew() && isPromotion() == orderItem.isPromotion() && Objects.equals(getProduct(), orderItem.getProduct());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getAmount(), getProduct(), getUnitPrice(), isNew(), isPromotion());
  }
}

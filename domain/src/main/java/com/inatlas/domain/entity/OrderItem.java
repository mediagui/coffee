package com.inatlas.domain.entity;

import lombok.EqualsAndHashCode;

import java.util.StringJoiner;


@EqualsAndHashCode
public class OrderItem{
  private int amount;
  private Product product;
  private double unitPrice;
  private boolean isNew;
  private boolean promotion;
  private boolean price;

  public OrderItem() {
  }

  public OrderItem(int amount, Product product) {
    this.amount = amount;
    this.product = product;
    this.unitPrice = product.getPrice();
    this.promotion=false;
  }

  public OrderItem(int amount, Product product, boolean isPromotion) {
    this.amount = amount;
    this.product = product;
    this.unitPrice = isPromotion||product.isPromotion() ? 0 : product.getPrice();
    this.promotion = isPromotion;

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

  public boolean isNew() {
    return isNew;
  }

  public void setNew(boolean aNew) {
    isNew = aNew;
  }

  public boolean isPromotion() {
    return promotion;
  }

  public void setPromotion(boolean promotion) {
    this.promotion = promotion;
  }

  public boolean isPrice() {
    return price;
  }

  public void setPrice(boolean price) {
    this.price = price;
  }

  public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
  }

  public double getUnitPrice() {
    return unitPrice;
  }

  public OrderItem(OrderItem other){
    this(other.getAmount(), other.getProduct(), other.isPromotion(), other.isNew());
  }

  public OrderItem(int amount, Product product, boolean isPromotion, boolean isNew) {
    this(amount,product,isPromotion);
    this.isNew = isNew;
  }


  public double getTotal() {
    return amount * unitPrice;
  }


  @Override
  public String toString() {
    return new StringJoiner(", ", OrderItem.class.getSimpleName() + "[", "]")
            .add("amount=" + amount)
            .add("product=" + product)
            .add("getTotal=" + getTotal())
            .toString();
  }


}

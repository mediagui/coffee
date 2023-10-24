package com.inatlas.domain.entity;

import java.util.Objects;
import java.util.StringJoiner;


public class Product {

  private int id;
  private String name;
  private double price;
  private String type;
  private boolean promotion;

  public Product() {
  }

  public Product(int id, String name, double price, boolean isPromotion, String type) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.promotion = isPromotion;
    this.type = type;
  }

  public Product(Product product){
    this.id = product.getId();
    this.name = product.getName();
    this.price = product.getPrice();
    this.promotion = product.isPromotion();
    this.type = product.getType();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public boolean isPromotion() {
    return promotion;
  }

  public void setPromotion(boolean promotion) {
    this.promotion = promotion;
  }

  @Override
  public String toString() {
    return new StringJoiner("", "", "")
            .add("\t")
            .add(String.valueOf(id))
            .add(". ")
            .add(name)
            .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Product)) return false;
    Product product = (Product) o;
    return getId() == product.getId() && Double.compare(getPrice(), product.getPrice()) == 0 && isPromotion() == product.isPromotion() && Objects.equals(getName(), product.getName()) && Objects.equals(getType(), product.getType());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getPrice(), getType(), isPromotion());
  }
}

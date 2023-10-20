package com.inatlas.domain.db.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductDB {

  @Id
  private int id;

  private String name;
  private double price;
  private String type;

  private boolean isPromotion;

  public ProductDB() {
  }



  public ProductDB(String name, double price, String type) {
    this.name = name;
    this.price = price;
    this.type = type;
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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public boolean isPromotion() {
    return isPromotion;
  }

  public void setPromotion(boolean promotion) {
    this.isPromotion = promotion;
  }
}

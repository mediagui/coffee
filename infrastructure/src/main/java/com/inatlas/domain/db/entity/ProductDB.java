package com.inatlas.domain.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;


@Entity
public class ProductDB {

  @Id
  private int id;
  private String name;
  private double price;
  private String type;
  private boolean promotion;


  public ProductDB() {
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

  public ProductDB(String name, double price, String type) {
    this.name = name;
    this.price = price;
    this.type = type;
  }


}

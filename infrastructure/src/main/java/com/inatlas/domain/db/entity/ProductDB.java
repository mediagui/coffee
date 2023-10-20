package com.inatlas.domain.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;

@Getter
@Setter
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


}

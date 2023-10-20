package com.inatlas.domain.db.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class OrderDB {

  @Id
  private Integer id;
  private double total;
  private LocalDateTime orderDate;
  private boolean complete;

  public OrderDB(){}

  public OrderDB(Integer id, double total) {
    this.id = id;
    this.total = total;
    this.orderDate = LocalDateTime.now();
  }
}

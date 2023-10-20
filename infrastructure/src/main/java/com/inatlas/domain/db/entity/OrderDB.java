package com.inatlas.domain.db.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class OrderDB {

  @Id
  private Integer id;
  private double total;

  private LocalDateTime orderDate;

  public OrderDB(){}

  public OrderDB(Integer id, double total) {
    this.id = id;
    this.total = total;
    this.orderDate = LocalDateTime.now();
  }
  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }
}

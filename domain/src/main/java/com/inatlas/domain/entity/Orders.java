package com.inatlas.domain.entity;

import java.util.Collections;
import java.util.List;

public class Orders {
  private List<Order> orderList;

  public Orders(List<Order> orderList) {
    this.orderList = orderList;
  }

  public List<Order> getOrderList() {
    return Collections.unmodifiableList(orderList) ;
  }

  public void setOrderList(List<Order> orderList) {
    this.orderList = orderList;
  }
}

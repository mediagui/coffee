package com.inatlas.domain.entity;

import java.util.Collections;
import java.util.List;

public class Menu {

  private List<Product> productList;

  public Menu(List<Product> productList) {
    this.productList = productList;
  }

  public List<Product> getProductList() {
    return Collections.unmodifiableList(productList);
  }

  public void setProductList(List<Product> productList) {
    this.productList = productList;
  }
}

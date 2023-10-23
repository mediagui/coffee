package com.inatlas.domain.entity;


public class CoffeeShop {
  private Menu menu;
  private Orders orders;


  public Menu getMenu() {
    return menu;
  }

  public void setMenu(Menu menu) {
    this.menu = menu;
  }

  public Orders getOrders() {
    return orders;
  }

  public void setOrders(Orders orders) {
    this.orders = orders;
  }

  // Otros métodos para la gestión del café.
}

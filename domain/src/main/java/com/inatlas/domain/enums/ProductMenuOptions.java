package com.inatlas.domain.enums;

public enum ProductMenuOptions implements IMenu {
  ENTER_MENU("E"),
  LIST_PRODUCTS("L"),
  FIRST_PRODUCT("1"),
  LAST_PRODUCT("7"),
  RETURN("R"),
  EXIT("X");


  private final String value;

  ProductMenuOptions(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static ProductMenuOptions fromVal(String i) {
    for (ProductMenuOptions option : values()) {
      if (option.getValue().equals(i)) {
        return option;
      }
    }
    return null;
  }
}

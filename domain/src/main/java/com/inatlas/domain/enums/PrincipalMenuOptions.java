package com.inatlas.domain.enums;

public enum PrincipalMenuOptions implements IMenu{
  ENTER_MENU(0, "Show menu"),
  LIST_ALL_PRODUCTS(1, "List all products"),
  CREATE_ORDER(2, "Create order"),

  EXIT(3, "Exit");


  private final int value;
  private final String description;

  PrincipalMenuOptions(int value, String description) {
    this.value = value;
    this.description = description;
  }

  public int getValue() {
    return value;
  }
  public String getDescription() {
    return description;
  }


  public static PrincipalMenuOptions fromInt(int i) {
    for (PrincipalMenuOptions option : values()) {
      if (option.getValue() == i) {
        return option;
      }
    }
    return null;
  }
}

package com.lawencon.bookleasing.constants;

/**
 * @author Rian Rivaldo Rumapea
 */
public enum Roles {
  ADMINISTRATOR("U001"),
  CASHIER("U002");

  private String code;

  private Roles(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

}

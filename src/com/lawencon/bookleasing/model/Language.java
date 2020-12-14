package com.lawencon.bookleasing.model;

/**
 * @author Rian Rivaldo Rumapea
 */
public class Language {

  private Long id;
  private String code;
  private String name;

  public Language(Long id) {
    this.id = id;
  }

  public Language(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public Language(Long id, String code) {
    this.id = id;
    this.code = code;
  }

  public Language(String code) {
    this.code = code;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}

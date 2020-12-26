package com.lawencon.bookleasing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Rian Rivaldo Rumapea
 */
@Entity
@Table(name = "tb_m_publisher")
@JsonInclude(Include.NON_NULL)
public class Publisher {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 10, nullable = false, unique = true)
  private String code;

  @Column(name = "publisher_name", nullable = false)
  private String name;

  private String city;

  public Publisher() {
  }

  public Publisher(Long id, String code, String name, String city) {
	this.id = id;
	this.code = code;
	this.name = name;
	this.city = city;
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

  public String getCity() {
	return city;
  }

  public void setCity(String city) {
	this.city = city;
  }

}

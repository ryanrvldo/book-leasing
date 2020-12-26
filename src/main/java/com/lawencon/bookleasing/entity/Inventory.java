package com.lawencon.bookleasing.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Rian Rivaldo Rumapea
 */
@Entity
@Table(name = "tb_m_inventory")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(allowSetters = true, value = { "hibernateLazyInitializer" })
public class Inventory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id")
  private Book book;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "status_id")
  private InventoryStatus status;

  public Inventory() {
  }

  public Inventory(Long id, Book book, InventoryStatus status) {
	this.id = id;
	this.book = book;
	this.status = status;
  }

  public Long getId() {
	return id;
  }

  public void setId(Long id) {
	this.id = id;
  }

  public Book getBook() {
	return book;
  }

  public void setBook(Book book) {
	this.book = book;
  }

  public InventoryStatus getStatus() {
	return status;
  }

  public void setStatus(InventoryStatus status) {
	this.status = status;
  }

}

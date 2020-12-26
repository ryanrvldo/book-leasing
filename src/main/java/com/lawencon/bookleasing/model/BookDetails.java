package com.lawencon.bookleasing.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.lawencon.bookleasing.entity.Author;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.Category;
import com.lawencon.bookleasing.entity.InventoryStatus;

/**
 * @author Rian Rivaldo
 */
@JsonInclude(value = Include.NON_NULL)
public class BookDetails {

  private Book book;
  private List<Category> categories;
  private List<Author> authors;
  private Integer stock;
  private InventoryStatus status;

  public Book getBook() {
	return book;
  }

  public void setBook(Book book) {
	this.book = book;
  }

  public List<Category> getCategories() {
	return categories;
  }

  public void setCategories(List<Category> categories) {
	this.categories = categories;
  }

  public List<Author> getAuthors() {
	return authors;
  }

  public void setAuthors(List<Author> authors) {
	this.authors = authors;
  }

  public Integer getStock() {
	return stock;
  }

  public void setStock(Integer stock) {
	this.stock = stock;
  }

  public InventoryStatus getStatus() {
	return status;
  }

  public void setStatus(InventoryStatus status) {
	this.status = status;
  }

}

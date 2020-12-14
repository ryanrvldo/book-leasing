package com.lawencon.bookleasing.model;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookCategory {

  private Book book;
  private Category category;

  public BookCategory(Category category) {
    this.category = category;
  }

  public BookCategory(Category category, Book book) {
    this.category = category;
    this.book = book;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

}

package com.lawencon.bookleasing.model;

/**
 * @author Rian Rivaldo Rumapea
 */
public class Inventory {

  private Long id;
  private Book book;
  private Bookshelf bookshelf;

  public Inventory() {}

  public Inventory(Long id) {
    this.id = id;
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

  public Bookshelf getBookshelf() {
    return bookshelf;
  }

  public void setBookshelf(Bookshelf bookshelf) {
    this.bookshelf = bookshelf;
  }

}

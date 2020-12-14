package com.lawencon.bookleasing.model;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookAuthor {

  private Author author;
  private Book book;

  public BookAuthor(Author author) {
    this.author = author;
  }

  public BookAuthor(Author author, Book book) {
    this.author = author;
    this.book = book;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

}

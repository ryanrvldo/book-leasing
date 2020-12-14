package com.lawencon.bookleasing.model;

import java.math.BigDecimal;

/**
 * @author Rian Rivaldo Rumapea
 */
public class Book {

  private Long id;
  private String title;
  private String description;
  private String isbn;
  private Double rating;
  private BigDecimal rentalCost;
  private BigDecimal replacementCost;
  private Short publicationYear;
  private Language language;
  private Publisher publisher;

  public Book() {}

  public Book(Long id) {
    this.id = id;
  }

  public Book(String title, String description, String isbn, Double rating, BigDecimal rentalCost,
      BigDecimal replacementCost, Short publicationYear) {
    this.title = title;
    this.description = description;
    this.isbn = isbn;
    this.rating = rating;
    this.rentalCost = rentalCost;
    this.replacementCost = replacementCost;
    this.publicationYear = publicationYear;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  public BigDecimal getRentalCost() {
    return rentalCost;
  }

  public void setRentalCost(BigDecimal rentalCost) {
    this.rentalCost = rentalCost;
  }

  public BigDecimal getReplacementCost() {
    return replacementCost;
  }

  public void setReplacementCost(BigDecimal replacementCost) {
    this.replacementCost = replacementCost;
  }

  public Short getPublicationYear() {
    return publicationYear;
  }

  public void setPublicationYear(Short publicationYear) {
    this.publicationYear = publicationYear;
  }

  public Language getLanguage() {
    return language;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public Publisher getPublisher() {
    return publisher;
  }

  public void setPublisher(Publisher publisher) {
    this.publisher = publisher;
  }

}

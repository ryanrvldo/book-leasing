package com.lawencon.bookleasing.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
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
 * @author Rian Rivaldo
 */
@Entity
@Table(name = "tb_m_book")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(allowSetters = true, value = { "hibernateLazyInitializer" })
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  private String description;

  @Column(nullable = false, unique = true)
  private String isbn;

  private Double rating;

  @Column(name = "rental_cost", nullable = false)
  private BigDecimal rentalCost;

  @Column(name = "replacement_cost", nullable = false)
  private BigDecimal replacementCost;

  @Column(name = "publication_year", nullable = false)
  private Short publicationYear;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "language_id")
  private Language language;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "publisher_id")
  private Publisher publisher;

  public Book() {
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

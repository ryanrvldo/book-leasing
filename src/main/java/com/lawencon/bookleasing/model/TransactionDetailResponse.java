package com.lawencon.bookleasing.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.lawencon.bookleasing.entity.Book;

/**
 * @author Rian Rivaldo
 */
@JsonInclude(content = Include.NON_NULL)
public class TransactionDetailResponse {

  private Book book;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime rentalDate;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime returnDate;

  public Book getBook() {
	return book;
  }

  public void setBook(Book book) {
	this.book = book;
  }

  public LocalDateTime getRentalDate() {
	return rentalDate;
  }

  public void setRentalDate(LocalDateTime rentalDate) {
	this.rentalDate = rentalDate;
  }

  public LocalDateTime getReturnDate() {
	return returnDate;
  }

  public void setReturnDate(LocalDateTime returnDate) {
	this.returnDate = returnDate;
  }

}

package com.lawencon.bookleasing.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Rian Rivaldo Rumapea
 */
public class Return {

  private Long id;
  private RentalHeader rentalHeader;
  private LocalDateTime returnedAt;
  private User user;
  private BigDecimal totalPrice;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public RentalHeader getRentalHeader() {
    return rentalHeader;
  }

  public void setRentalHeader(RentalHeader rentalHeader) {
    this.rentalHeader = rentalHeader;
  }

  public LocalDateTime getReturnedAt() {
    return returnedAt;
  }

  public void setReturnedAt(LocalDateTime returnedAt) {
    this.returnedAt = returnedAt;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

}

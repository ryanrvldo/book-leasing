package com.lawencon.bookleasing.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RentalDetail {

  private Long id;
  private RentalHeader rentalHeader;
  private Inventory inventory;
  private BigDecimal totalPrice;
  private LocalDateTime rentalDate;
  private LocalDateTime returnDate;

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

  public Inventory getInventory() {
    return inventory;
  }

  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
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

package com.lawencon.bookleasing.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Rian Rivaldo Rumapea
 */
@Entity
@Table(name = "tb_r_dtl_rental")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(allowSetters = true, value = { "hibernateLazyInitializer" })
public class RentalDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "hdr_id", nullable = false)
  private RentalHeader rentalHeader;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "inventory_id", nullable = false)
  private Inventory inventory;

  @Column(name = "total_price")
  private BigDecimal totalPrice;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "rental_date", nullable = false)
  private LocalDateTime rentalDate;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "return_date", nullable = false)
  private LocalDateTime returnDate;

  public RentalDetail() {
  }

  public RentalDetail(Long id, RentalHeader rentalHeader, Inventory inventory, BigDecimal totalPrice,
      LocalDateTime rentalDate, LocalDateTime returnDate) {
	this.id = id;
	this.rentalHeader = rentalHeader;
	this.inventory = inventory;
	this.totalPrice = totalPrice;
	this.rentalDate = rentalDate;
	this.returnDate = returnDate;
  }

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

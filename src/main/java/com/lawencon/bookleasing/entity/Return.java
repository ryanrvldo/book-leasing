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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Rian Rivaldo Rumapea
 */
@Entity
@Table(name = "tb_r_return")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(allowSetters = true, value = { "hibernateLazyInitializer", "rentalHeader", "category" })
public class Return {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "hdr_id", nullable = false)
  private RentalHeader rentalHeader;

  @Column(name = "returned_date", nullable = false)
  private LocalDateTime returnedDate;

  @Column(name = "total_price", nullable = false)
  private BigDecimal totalPrice;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  public Return() {
  }

  public Return(Long id, RentalHeader rentalHeader, LocalDateTime returnedDate, BigDecimal totalPrice, User user) {
	this.id = id;
	this.rentalHeader = rentalHeader;
	this.returnedDate = returnedDate;
	this.totalPrice = totalPrice;
	this.user = user;
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

  public LocalDateTime getReturnedDate() {
	return returnedDate;
  }

  public void setReturnedDate(LocalDateTime returnedDate) {
	this.returnedDate = returnedDate;
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

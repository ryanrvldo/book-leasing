package com.lawencon.bookleasing.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Rian Rivaldo
 */
@JsonInclude(content = Include.NON_NULL)
public class TransactionDetailRequest {

  private Long itemId;
  private Long rentalDays;

  public Long getItemId() {
	return itemId;
  }

  public void setItemId(Long itemId) {
	this.itemId = itemId;
  }

  public Long getRentalDays() {
	return rentalDays;
  }

  public void setRentalDays(Long rentalDays) {
	this.rentalDays = rentalDays;
  }

}

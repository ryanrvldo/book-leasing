package com.lawencon.bookleasing.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.lawencon.bookleasing.entity.Profile;

/**
 * @author Rian Rivaldo
 */
@JsonInclude(value = Include.NON_NULL)
public class TransactionRequest {

  private Profile customer;
  private List<TransactionDetailRequest> items;
  private Long userId;

  public Profile getCustomer() {
	return customer;
  }

  public void setCustomer(Profile customer) {
	this.customer = customer;
  }

  public List<TransactionDetailRequest> getItems() {
	return items;
  }

  public void setItems(List<TransactionDetailRequest> items) {
	this.items = items;
  }

  public Long getUserId() {
	return userId;
  }

  public void setUserId(Long userId) {
	this.userId = userId;
  }

}

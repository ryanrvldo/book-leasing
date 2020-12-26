package com.lawencon.bookleasing.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.lawencon.bookleasing.entity.Profile;

/**
 * @author Rian Rivaldo
 */
@JsonInclude(value = Include.NON_NULL)
public class TransactionResponse {

  private Profile customer;
  private List<TransactionDetailResponse> detailItems;

  public Profile getCustomer() {
	return customer;
  }

  public void setCustomer(Profile customer) {
	this.customer = customer;
  }

  public List<TransactionDetailResponse> getDetailItems() {
	return detailItems;
  }

  public void setDetailItems(List<TransactionDetailResponse> detailItems) {
	this.detailItems = detailItems;
  }

}

package com.lawencon.bookleasing.service;

import java.math.BigDecimal;

import com.lawencon.bookleasing.model.RentalHeader;
import com.lawencon.bookleasing.model.Return;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface ReturnBookService {

  Return checkReceiptNumber(RentalHeader header) throws Exception;

  BigDecimal getTotalRentalCost(String receiptNumber) throws Exception;

  void addReturnBook(RentalHeader header, Return returnData) throws Exception;

}

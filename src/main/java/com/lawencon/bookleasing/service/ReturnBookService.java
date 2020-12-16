package com.lawencon.bookleasing.service;

import java.math.BigDecimal;

import com.lawencon.bookleasing.entity.RentalHeader;
import com.lawencon.bookleasing.entity.Return;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface ReturnBookService {

  Return checkReceiptNumber(RentalHeader header) throws Exception;

  BigDecimal getTotalRentalCost(String receiptNumber) throws Exception;

  void addReturnBook(RentalHeader header, Return returnData) throws Exception;

}

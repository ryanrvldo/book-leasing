package com.lawencon.bookleasing.service.impl;

import java.math.BigDecimal;
import java.util.Optional;

import com.lawencon.bookleasing.model.RentalHeader;
import com.lawencon.bookleasing.model.Return;
import com.lawencon.bookleasing.service.RentalHeaderService;
import com.lawencon.bookleasing.service.ReturnBookService;
import com.lawencon.bookleasing.service.ReturnService;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ReturnBookServiceImpl implements ReturnBookService {

  private ReturnService returnService = new ReturnServiceImpl();

  @Override
  public Return checkReceiptNumber(RentalHeader header) throws Exception {
    Optional.ofNullable(header.getReceipt())
        .orElseThrow(() -> new IllegalArgumentException("Receipt Number must be not null."));

    RentalHeaderService headerService = new RentalHeaderServiceImpl();
    RentalHeader resultHeader = Optional.ofNullable(headerService.get(header))
        .orElseThrow(() -> new NullPointerException(
            "Receipt Number is invalid. Make sure you have inputted valid receipt number."));
    Return returnData = new Return();
    returnData.setRentalHeader(resultHeader);
    return Optional.ofNullable(returnService.get(returnData))
        .orElseThrow(() -> new IllegalArgumentException("Books with that receipt number have been returned!"));
  }

  @Override
  public BigDecimal getTotalRentalCost(String receiptNumber) throws Exception {
    return Optional.ofNullable(returnService.getTotalRentalCost(receiptNumber))
        .orElseThrow();
  }

  @Override
  public void addReturnBook(RentalHeader header, Return returnData) throws Exception {
    Optional.ofNullable(returnService.add(returnData))
        .orElseThrow(() -> new Exception("Failed to add data to database."));
  }

}

package com.lawencon.bookleasing.service;

import com.lawencon.bookleasing.entity.RentalHeader;
import com.lawencon.bookleasing.entity.Return;

import java.math.BigDecimal;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface ReturnBookService {

	Return checkReceiptNumber(RentalHeader header) throws Exception;

	BigDecimal getTotalRentalCost(String receiptNumber) throws Exception;

	void addReturnBook(RentalHeader header, Return returnData) throws Exception;

}

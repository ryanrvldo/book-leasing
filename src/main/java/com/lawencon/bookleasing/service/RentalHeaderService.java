package com.lawencon.bookleasing.service;

import com.lawencon.bookleasing.entity.RentalHeader;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface RentalHeaderService extends BaseService<RentalHeader> {

  RentalHeader getRentalHeaderByReceiptNumber(String receiptNumber) throws Exception;

}

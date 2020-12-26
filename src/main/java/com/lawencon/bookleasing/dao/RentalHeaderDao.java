package com.lawencon.bookleasing.dao;

import com.lawencon.bookleasing.entity.RentalHeader;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface RentalHeaderDao extends BaseDao<RentalHeader> {

  RentalHeader findByReceiptNumber(String receiptNumber) throws Exception;

}

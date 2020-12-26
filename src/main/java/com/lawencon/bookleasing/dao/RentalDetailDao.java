package com.lawencon.bookleasing.dao;

import java.util.List;

import com.lawencon.bookleasing.entity.RentalDetail;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface RentalDetailDao extends BaseDao<RentalDetail> {

  List<RentalDetail> findAllByReceiptNumber(String receiptNumber) throws Exception;

}

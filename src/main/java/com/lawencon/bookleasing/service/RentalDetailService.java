package com.lawencon.bookleasing.service;

import java.util.List;

import com.lawencon.bookleasing.entity.RentalDetail;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface RentalDetailService extends BaseService<RentalDetail> {

  List<RentalDetail> findAllByReceiptNumber(String receiptNumber) throws Exception;

}

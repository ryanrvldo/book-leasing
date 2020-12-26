package com.lawencon.bookleasing.service;

import java.math.BigDecimal;

import com.lawencon.bookleasing.entity.Return;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface ReturnService extends BaseService<Return> {

  Return getReturnByHeaderId(Long headerId) throws Exception;

  BigDecimal getTotalRentalCost(String Receipt) throws Exception;

}

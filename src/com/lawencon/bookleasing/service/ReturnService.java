package com.lawencon.bookleasing.service;

import java.math.BigDecimal;

import com.lawencon.bookleasing.model.Return;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface ReturnService extends BaseDaoService<Return> {

  BigDecimal getTotalRentalCost(String Receipt) throws Exception;

}

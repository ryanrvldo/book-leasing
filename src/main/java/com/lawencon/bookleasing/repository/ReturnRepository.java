package com.lawencon.bookleasing.repository;

import java.math.BigDecimal;

import com.lawencon.bookleasing.entity.Return;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface ReturnRepository extends BaseRepository<Return> {

  BigDecimal getTotalRentalCost(String Receipt) throws Exception;

}

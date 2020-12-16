package com.lawencon.bookleasing.repository;

import com.lawencon.bookleasing.entity.Return;

import java.math.BigDecimal;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface ReturnRepository extends BaseRepository<Return> {

	BigDecimal getTotalRentalCost(String Receipt) throws Exception;

}

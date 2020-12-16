package com.lawencon.bookleasing.dao;

import com.lawencon.bookleasing.entity.Return;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface ReturnDao extends BaseDao<Return> {

  Double getRentalCost(String receiptNumber) throws Exception;

}

package com.lawencon.bookleasing.dao;

import com.lawencon.bookleasing.model.Return;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface ReturnDao extends BaseDao<Return> {

  Double getRentalCost(String receiptNumber) throws Exception;

}

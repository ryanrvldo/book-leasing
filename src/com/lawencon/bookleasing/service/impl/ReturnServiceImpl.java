package com.lawencon.bookleasing.service.impl;

import java.math.BigDecimal;

import com.lawencon.bookleasing.dao.ReturnDao;
import com.lawencon.bookleasing.dao.impl.ReturnDaoImpl;
import com.lawencon.bookleasing.model.Return;
import com.lawencon.bookleasing.service.ReturnService;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ReturnServiceImpl implements ReturnService {

  private ReturnDao dao = new ReturnDaoImpl();

  @Override
  public BigDecimal getTotalRentalCost(String receiptNumber) throws Exception {
    return BigDecimal.valueOf(dao.getRentalCost(receiptNumber));
  }

  @Override
  public Return add(Return item) throws Exception {
    return dao.insert(item);
  }

  @Override
  public Return get(Return item) throws Exception {
    return dao.get(item);
  }

}

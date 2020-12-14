package com.lawencon.bookleasing.service.impl;

import com.lawencon.bookleasing.dao.RentalDetailDao;
import com.lawencon.bookleasing.dao.impl.RentalDetailDaoImpl;
import com.lawencon.bookleasing.model.RentalDetail;
import com.lawencon.bookleasing.service.RentalDetailService;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RentalDetailServiceImpl implements RentalDetailService {

  private RentalDetailDao dao = new RentalDetailDaoImpl();;

  @Override
  public RentalDetail add(RentalDetail newDetail) throws Exception {
    return dao.insert(newDetail);
  }

  @Override
  public RentalDetail get(RentalDetail item) throws Exception {
    throw new IllegalAccessException("Not implemented yet. Under development.");
  }

}

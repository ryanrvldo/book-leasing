package com.lawencon.bookleasing.service.impl;

import com.lawencon.bookleasing.dao.RentalHeaderDao;
import com.lawencon.bookleasing.dao.impl.RentalHeaderDaoImpl;
import com.lawencon.bookleasing.model.RentalHeader;
import com.lawencon.bookleasing.service.RentalHeaderService;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RentalHeaderServiceImpl implements RentalHeaderService {

  private RentalHeaderDao dao = new RentalHeaderDaoImpl();

  @Override
  public RentalHeader add(RentalHeader rentalHeader) throws Exception {
    return dao.insert(rentalHeader);
  }

  @Override
  public RentalHeader get(RentalHeader rentalHeader) throws Exception {
    return dao.get(rentalHeader);
  }

}

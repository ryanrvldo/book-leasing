package com.lawencon.bookleasing.service.impl;

import com.lawencon.bookleasing.dao.ProfileDao;
import com.lawencon.bookleasing.dao.impl.ProfileDaoImpl;
import com.lawencon.bookleasing.model.Profile;
import com.lawencon.bookleasing.service.ProfileService;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ProfileServiceImpl implements ProfileService {

  private ProfileDao dao = new ProfileDaoImpl();

  @Override
  public Profile add(Profile newProfile) throws Exception {
    return dao.insert(newProfile);
  }

  @Override
  public Profile get(Profile item) throws Exception {
    throw new IllegalAccessException("Not implemented yet. Under development.");
  }

}

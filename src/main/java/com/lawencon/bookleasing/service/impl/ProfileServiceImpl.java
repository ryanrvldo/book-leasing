package com.lawencon.bookleasing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.bookleasing.dao.ProfileDao;
import com.lawencon.bookleasing.entity.Profile;
import com.lawencon.bookleasing.service.ProfileService;

/**
 * @author Rian Rivaldo Rumapea
 */
@Service
public class ProfileServiceImpl implements ProfileService {

  private final ProfileDao dao;

  @Autowired
  public ProfileServiceImpl(@Qualifier("profile-jpa") ProfileDao dao) {
	this.dao = dao;
  }

  @Override
  public void create(Profile data) throws Exception {
	Profile validatedProfile = getProfileByEmailAndPhone(data.getEmail(), data.getPhone());
	if (validatedProfile != null) {
	  data = validatedProfile;
	} else {
	  this.dao.insert(data);
	}
  }

  @Override
  public Profile getProfileByEmailAndPhone(String email, String phone) throws Exception {
	return validateGet(() -> this.dao.findByEmailAndPhone(email, phone));
  }

  @Override
  public void update(Profile data) throws Exception {
	this.dao.update(data);
  }

  @Override
  public void delete(Profile data) throws Exception {
	this.dao.delete(data);
  }

  @Override
  public List<Profile> getAll() throws Exception {
	return dao.findAll();
  }

}

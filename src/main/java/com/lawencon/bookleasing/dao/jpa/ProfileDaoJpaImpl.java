package com.lawencon.bookleasing.dao.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.ProfileDao;
import com.lawencon.bookleasing.entity.Profile;
import com.lawencon.bookleasing.repository.ProfileRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("profile-jpa")
public class ProfileDaoJpaImpl implements ProfileDao {

  private final ProfileRepository repository;

  @Autowired
  public ProfileDaoJpaImpl(ProfileRepository repository) {
	this.repository = repository;
  }

  @Override
  public Profile findById(Long id) throws Exception {
	return repository.findById(id).get();
  }

  @Override
  public void insert(Profile data) throws Exception {
	repository.save(data);
  }

  @Override
  public void update(Profile data) throws Exception {
	repository.save(data);
  }

  @Override
  public void delete(Profile data) throws Exception {
	repository.delete(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	repository.deleteById(id);
  }

  @Override
  public List<Profile> findAll() throws Exception {
	return repository.findAll();
  }

  @Override
  public Profile findByEmailAndPhone(String email, String phone) throws Exception {
	return repository.findByEmailAndPhone(email, phone);
  }

}

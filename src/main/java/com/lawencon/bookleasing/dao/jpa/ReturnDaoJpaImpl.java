package com.lawencon.bookleasing.dao.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.ReturnDao;
import com.lawencon.bookleasing.entity.Return;
import com.lawencon.bookleasing.repository.ReturnRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("return-jpa")
public class ReturnDaoJpaImpl implements ReturnDao {

  private final ReturnRepository repository;

  @Autowired
  public ReturnDaoJpaImpl(ReturnRepository repository) {
	this.repository = repository;
  }

  @Override
  public Double getRentalCost(String receiptNumber) throws Exception {
	return null;
  }

  @Override
  public Return findById(Long id) throws Exception {
	return repository.findById(id).get();
  }

  @Override
  public Return findByHeaderId(Long headerId) throws Exception {
	return repository.findByHeaderId(headerId);
  }

  @Override
  public void insert(Return data) throws Exception {
	repository.save(data);
  }

  @Override
  public void update(Return data) throws Exception {
	repository.save(data);
  }

  @Override
  public void delete(Return data) throws Exception {
	repository.delete(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	repository.deleteById(id);
  }

  @Override
  public List<Return> findAll() throws Exception {
	return repository.findAll();
  }

}

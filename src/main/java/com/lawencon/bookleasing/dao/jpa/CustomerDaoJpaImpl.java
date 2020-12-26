package com.lawencon.bookleasing.dao.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.CustomerDao;
import com.lawencon.bookleasing.entity.Customer;
import com.lawencon.bookleasing.repository.CustomerRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("customer-jpa")
public class CustomerDaoJpaImpl implements CustomerDao {

  private final CustomerRepository repository;

  @Autowired
  public CustomerDaoJpaImpl(CustomerRepository repository) {
	this.repository = repository;
  }

  @Override
  public Customer findById(Long id) throws Exception {
	return repository.findById(id).get();
  }

  @Override
  public Customer findByEmail(String email) throws Exception {
	return repository.findByEmail(email);
  }

  @Override
  public void insert(Customer data) throws Exception {
	repository.save(data);
  }

  @Override
  public void update(Customer data) throws Exception {
	repository.save(data);
  }

  @Override
  public void delete(Customer data) throws Exception {
	repository.delete(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	repository.deleteById(id);
  }

  @Override
  public List<Customer> findAll() throws Exception {
	return repository.findAll();
  }

}

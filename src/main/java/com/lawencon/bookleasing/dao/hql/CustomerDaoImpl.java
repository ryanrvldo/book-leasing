package com.lawencon.bookleasing.dao.hql;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.CustomerDao;
import com.lawencon.bookleasing.entity.Customer;

/**
 * @author Rian Rivaldo
 */
@Repository("customer-hql")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

  public CustomerDaoImpl() {
	super(Customer.class);
  }

  @Override
  public Customer findById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "FROM Customer ",
	    "WHERE id = ?1 ");
	return getSingleResultById(hql, id);
  }

  @Override
  public Customer findByEmail(String email) throws Exception {
	String hql = buildQueryOf(
	    "FROM Customer c ",
	    "WHERE lower(c.profile.email) = ?1 ");
	return getSingleResultFromEntity(hql, email);
  }

  @Override
  public void insert(Customer data) throws Exception {
	insertData(data);
  }

  @Override
  public void update(Customer data) throws Exception {
	mergeUpdateData(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "FROM Category ",
	    "WHERE id = ?1 ");
	deleteEntityById(hql, id);
  }

  @Override
  public void delete(Customer data) throws Exception {
	deleteData(data);
  }

  @Override
  public List<Customer> findAll() throws Exception {
	return getAllData();
  }

}

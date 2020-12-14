package com.lawencon.bookleasing.service.impl;

import com.lawencon.bookleasing.dao.CustomerDao;
import com.lawencon.bookleasing.dao.impl.CustomerDaoImpl;
import com.lawencon.bookleasing.model.Customer;
import com.lawencon.bookleasing.service.CustomerService;

/**
 * @author Rian Rivaldo Rumapea
 */
public class CustomerServiceImpl implements CustomerService {

  private CustomerDao dao = new CustomerDaoImpl();

  @Override
  public Customer add(Customer newCustomer) throws Exception {
    return dao.insert(newCustomer);
  }

  @Override
  public Customer get(Customer item) throws Exception {
    throw new IllegalAccessException("Not implemented yet. Under development.");
  }

}

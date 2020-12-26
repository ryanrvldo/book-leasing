package com.lawencon.bookleasing.service;

import com.lawencon.bookleasing.entity.Customer;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface CustomerService extends BaseService<Customer> {

  Customer getCustomerByEmail(String email) throws Exception;

}
